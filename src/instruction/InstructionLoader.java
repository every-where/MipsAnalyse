package instruction;

import exception.LoadException;
import exception.bit.BitException;
import exception.rule.RuleException;
import instruction.bit.BitFlag;
import instruction.instruction.Instruction;
import instruction.rule.MatchRule;
import instruction.rule.Rule;
import instruction.rule.UnMatchRule;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InstructionLoader {
    private final HashMap<String, BitFlag> bitFlagMap;
    private final ArrayList<Instruction> instructions;
    public InstructionLoader(String fileName) throws BitException, RuleException, LoadException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = factory.newDocumentBuilder();
            File f = new File(fileName);
            Document doc = db.parse(f);
            bitFlagMap = loadBitFlags(doc);
            instructions = loadInstructions(doc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new LoadException(fileName);
        }
    }

    public HashMap<String, BitFlag> getBitFlagMap() {
        return bitFlagMap;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    private HashMap<String, BitFlag> loadBitFlags(Document doc) throws BitException {
        HashMap<String, BitFlag> bitFlagMap = new HashMap<>();
        NodeList bitFlagsNodeList = doc.getElementsByTagName("bitFlags");
        if (bitFlagsNodeList.getLength() == 0) {
            return bitFlagMap;
        }
        Node bitFlagsNode = bitFlagsNodeList.item(bitFlagsNodeList.getLength() - 1);
        NodeList bitFlagNodeList = bitFlagsNode.getChildNodes();
        for (int i = 0;i < bitFlagNodeList.getLength();i++) {
            Node bitFlagNode = bitFlagNodeList.item(i);
            if (bitFlagNode.getNodeType() == Node.ELEMENT_NODE) {
                if (bitFlagNode.getNodeName().equals("bitFlag")) {
                    BitFlag bitFlag = loadBitFlag(bitFlagNode);
                    bitFlagMap.put(bitFlag.getName(), bitFlag);
                }
            }
        }
        return bitFlagMap;
    }

    private BitFlag loadBitFlag(Node bitFlagNode) throws BitException {
        NodeList bitFlagInformationNodeList = bitFlagNode.getChildNodes();
        String name = "";
        int start = -1;
        int end = -1;
        for (int j = 0;j < bitFlagInformationNodeList.getLength();j++) {
            Node bitFlagInformationNode = bitFlagInformationNodeList.item(j);
            if (bitFlagInformationNode.getNodeType() == Node.ELEMENT_NODE) {
                switch (bitFlagInformationNode.getNodeName()) {
                    case "name" -> name = bitFlagInformationNode.getFirstChild().getNodeValue();
                    case "start" -> start = Integer.parseInt(bitFlagInformationNode.getFirstChild().getNodeValue());
                    case "end" -> end = Integer.parseInt(bitFlagInformationNode.getFirstChild().getNodeValue());
                }
            }
        }
        return new BitFlag(name, start, end);
    }

    private ArrayList<Instruction> loadInstructions(Document doc) throws RuleException {
        ArrayList<Instruction> instructions = new ArrayList<>();
        NodeList instructionsNodeList = doc.getElementsByTagName("instructions");
        if (instructionsNodeList.getLength() == 0) {
            return instructions;
        }
        Node instructionsNode = instructionsNodeList.item(instructionsNodeList.getLength() - 1);
        NodeList instructionNodeList = instructionsNode.getChildNodes();
        for (int i = 0;i < instructionNodeList.getLength();i++) {
            Node instructionNode = instructionNodeList.item(i);
            if (instructionNode.getNodeType() == Node.ELEMENT_NODE) {
                if (instructionNode.getNodeName().equals("instruction")) {
                    instructions.add(loadInstruction(instructionNode));
                }
            }
        }
        return instructions;
    }

    private Instruction loadInstruction(Node instructionNode) throws RuleException {
        String name = null;
        ArrayList<Rule> rules = null;
        String output = null;
        NodeList instructionInformationNodeList = instructionNode.getChildNodes();
        for (int i = 0;i < instructionInformationNodeList.getLength();i++) {
            Node instructionInformationNode = instructionInformationNodeList.item(i);
            if (instructionInformationNode.getNodeType() == Node.ELEMENT_NODE) {
                switch (instructionInformationNode.getNodeName()) {
                    case "name" -> name = instructionInformationNode.getFirstChild().getNodeValue();
                    case "rules" -> rules = loadRules(instructionInformationNode.getChildNodes());
                    case "output" -> output = instructionInformationNode.getFirstChild().getNodeValue();
                }
            }
        }
        return new Instruction(name, rules, output);
    }

    private ArrayList<Rule> loadRules(NodeList rulesNodelist) throws RuleException {
        ArrayList<Rule> rules = new ArrayList<>();
        for (int i = 0;i < rulesNodelist.getLength();i++) {
            Node ruleNode = rulesNodelist.item(i);
            if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
                if (ruleNode.getNodeName().equals("rule")) {
                    rules.add(loadRule(ruleNode));
                }
            }
        }
        return rules;
    }

    private Rule loadRule(Node ruleNode) throws RuleException {
        NodeList ruleInformationNodeList = ruleNode.getChildNodes();
        boolean match = true;
        int start = 31;
        int end = 0;
        String compareCode = "0000 0000 0000 0000 0000 0000 0000 0000";
        for (int i = 0;i < ruleInformationNodeList.getLength();i++) {
            Node ruleInformationNode = ruleInformationNodeList.item(i);
            if (ruleInformationNode.getNodeType() == Node.ELEMENT_NODE) {
                switch (ruleInformationNode.getNodeName()) {
                    case "match" -> match = Boolean.parseBoolean(ruleInformationNode.getFirstChild().getNodeValue());
                    case "start" -> start = Integer.parseInt(ruleInformationNode.getFirstChild().getNodeValue());
                    case "end" -> end = Integer.parseInt(ruleInformationNode.getFirstChild().getNodeValue());
                    case "compareCode" -> compareCode = ruleInformationNode.getFirstChild().getNodeValue();
                }
            }
        }
        if (match) {
            return new MatchRule(start, end, compareCode);
        } else {
            return new UnMatchRule(start, end, compareCode);
        }
    }
}
