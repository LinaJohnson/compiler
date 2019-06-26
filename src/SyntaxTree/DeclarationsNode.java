package SyntaxTree;

import SimpleScanner.TokenType;
import java.util.ArrayList;

/**
 *
 * @author LinaVo
 */
public class DeclarationsNode extends SyntaxTreeNode {
    
    // instantiation variable
    private ArrayList<VariableNode> vars;
    private TokenType type;

    // appending more than one list of declarations together
    public void addVars(ArrayList<VariableNode> vars) {
        this.vars.addAll(vars);
    }
    
    /**
     * auto generated getter
     * @return 
     */
    public ArrayList<VariableNode> getVars() {
        return vars;
    }
    public TokenType getType() {
        return type;
    }

    /**
     * auto generated setter
     * @param vars 
     */
    public void setVars(ArrayList<VariableNode> vars) {
        this.vars = vars;
    }
    public void setType(TokenType type) {
        this.type = type;
    }

    /**
     * constructors
     * @param vars 
     */
    public DeclarationsNode(ArrayList<VariableNode> vars) {
        this.vars = vars;
    }
    public DeclarationsNode() {
        this.vars = new ArrayList<VariableNode>();
    }
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "DeclarationsNode\n";
        for(VariableNode vn:this.vars){
            answer += vn.indentedToString(level + 1);
        }
        return(answer);
    }
    
}
