package SyntaxTree;
import java.util.ArrayList;
/**
 * Created by LinaVo on 4/9/16.
 */
public class ProcedureStatementNode extends StatementNode {
    private VariableNode id;
    private ArrayList<ExpressionNode> expressionList;

    public void setId(VariableNode id) {
        this.id = id;
    }
    public void setExpressionList(ArrayList<ExpressionNode> expressionList) {
        this.expressionList = expressionList;
    }
    public VariableNode getId() {
        return id;
    }
    public ArrayList<ExpressionNode> getexpressionList() {
        return expressionList;
    }
    public ProcedureStatementNode(VariableNode id, ArrayList<ExpressionNode> expressionList) {
        this.id = id;
        this.expressionList = expressionList;
    }
    public ProcedureStatementNode() {
        this.id = null;
        this.expressionList = null;
    }
}
