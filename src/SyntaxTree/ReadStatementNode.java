package SyntaxTree;

/**
 *
 * @author LinaVo
 */
public class ReadStatementNode extends StatementNode {
    // private variable
    private VariableNode id;
    /*
    * auto generated getter
    */
    public VariableNode getId() {
        return id;
    }
    /*
    * auto generated setter
    */
    public void setId(VariableNode id) {
        this.id = id;
    }
    /*
    * constructors for ReadStatementNode
    */
    public ReadStatementNode(VariableNode id) {
        this.id = id;
    }
    public ReadStatementNode() {
        this.id = new VariableNode();
    }
    /**
     * for leaf node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "ReadStatementNode:" + this.id + "\n";
        return(answer);
    }
}
