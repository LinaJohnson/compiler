package SyntaxTree;
import java.util.ArrayList;

/**
 *
 * @author LinaVo
 */
public class CompoundStatementNode extends StatementNode {
    
    // instantiation variable
    private ArrayList<StatementNode> statements;

    /**
     * auto generated getter
     * @return 
     */
    public ArrayList<StatementNode> getStatements() {
        return statements;
    }

    /**
     * auto generated setter
     * @param statements 
     */
    public void setStatements(ArrayList<StatementNode> statements) {
        this.statements = statements;
    }

    /**
     * constructors
     * @param statements 
     */
    public CompoundStatementNode(ArrayList<StatementNode> statements) {
        this.statements = statements;
    }
    public CompoundStatementNode() {
        this.statements = statements;
    }
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "CompoundStatementNode\n";
        for(StatementNode sn:this.statements){
            answer += sn.indentedToString(level + 1);
        }
        return(answer);
    }
}
