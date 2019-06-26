package SyntaxTree;

import SimpleScanner.TokenType;
import java.util.ArrayList;

/**
 * coming back to this at a later time
 * @author LinaVo
 */
public class FunctionNode extends SubProgramNode {
    
    // private variables
    private String id;
    private ArrayList<VariableNode> functionArguements;
    private TokenType standard_type;
 
    /**
     * auto generated getters
     */
    public String getId() {
        return id;
    }
    public ArrayList<VariableNode> getFunctionArguements() {
        return functionArguements;
    }
    public TokenType isStandard_type() {
        return standard_type;
    }
    
    /**
     * auto generated setters
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setFunctionArguements(ArrayList<VariableNode> functionArguements) {
        this.functionArguements = functionArguements;
    }

    public void setStandard_type(TokenType standard_type) {
        this.standard_type = standard_type;
    }

     /**
     * constructors for function
     */
    public FunctionNode(String id, ArrayList<VariableNode> functionArguements, TokenType standard_type) {
        this.id = id;
        this.functionArguements = functionArguements;
        this.standard_type = standard_type;
    }
    public FunctionNode() {
        this.id = "";
        this.functionArguements = new ArrayList<VariableNode>();
        this.standard_type = null;
    }
    
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "FunctionNode: " + this.id + "\n";
        for(VariableNode vn:this.functionArguements){
            answer += vn.indentedToString(level + 1);
        }
        return(answer);
    }
    
}
