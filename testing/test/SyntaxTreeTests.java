import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import SyntaxTree.*;
import SimpleScanner.TokenType;
import java.util.ArrayList;

/**
 *
 * @author LinaVo
 */
public class SyntaxTreeTests {
    
    public SyntaxTreeTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of indentedToString method, of class ExpressionNode.
     */
    @Test
    public void testIndentedToString() {
        
        // variable nodes in example handed out in class
        VariableNode dollars = new VariableNode("dollars");
        VariableNode yen = new VariableNode("yen");
        VariableNode bitcoins = new VariableNode("bitcoins");
        
        // value nodes in example handed out in class
        ValueNode onemillion = new ValueNode("1000000");
        ValueNode onehundredtwo = new ValueNode("102");
        ValueNode fourhundred = new ValueNode("400");
        
        // operation nodes in example handed out in class
        OperationNode multiply = new OperationNode(TokenType.MULTIPLY);
        OperationNode divide = new OperationNode(TokenType.DIVIDE);
        
        // setting children nodes for operation nodes
        multiply.setLeft(dollars);
        multiply.setRight(onehundredtwo);
        divide.setLeft(dollars);
        divide.setRight(fourhundred);

        // creating then adding variables into varaible list for declaration node
        ArrayList<VariableNode> vars = new ArrayList<VariableNode>();
        vars.add(dollars);
        vars.add(yen);
        vars.add(bitcoins);

        // assignment statement nodes
        AssignmentStatementNode left = new AssignmentStatementNode(dollars,onemillion);
        AssignmentStatementNode middle = new AssignmentStatementNode(yen,multiply);
        AssignmentStatementNode right = new AssignmentStatementNode(bitcoins,divide);
        
        ArrayList<StatementNode> assignmentStatements = new ArrayList<StatementNode>();
        assignmentStatements.add(left);
        assignmentStatements.add(middle);
        assignmentStatements.add(right);
        
        DeclarationsNode decNode = new DeclarationsNode(vars);
        SubProgramDeclarationsNode subDecNode = new SubProgramDeclarationsNode();
        CompoundStatementNode compStNode = new CompoundStatementNode(assignmentStatements);
        
        ProgramNode sample = new ProgramNode("sample",decNode, compStNode,subDecNode);
        
        String expected = "ProgramNode: sample\n" +
                          "|-- DeclarationsNode\n" +
                          "|-- -- VariableNode: dollars\n" +
                          "|-- -- VariableNode: yen\n" +
                          "|-- -- VariableNode: bitcoins\n" +
                          "|-- SubprogramDeclarationsNode\n" +
                          "|-- CompoundStatementNode\n" +
                          "|-- -- AssignmentStatementNode\n" +
                          "|-- -- -- VariableNode: dollars\n" +
                          "|-- -- -- ValueNode: 1000000\n" +
                          "|-- -- AssignmentStatementNode\n" +
                          "|-- -- -- VariableNode: yen\n" +
                          "|-- -- -- OperationNode: MULTIPLY\n" +
                          "|-- -- -- -- VariableNode: dollars\n" +
                          "|-- -- -- -- ValueNode: 102\n" +
                          "|-- -- AssignmentStatementNode\n" +
                          "|-- -- -- VariableNode: bitcoins\n" +
                          "|-- -- -- OperationNode: DIVIDE\n" +
                          "|-- -- -- -- VariableNode: dollars\n" +
                          "|-- -- -- -- ValueNode: 400\n";
                
        String actual = sample.indentedToString(0);
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}
