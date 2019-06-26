package SyntaxTree;

import java.util.ArrayList;

/**
 * @author LinaVo
 */
public class ProcedureNode extends SubProgramNode {

    // private variables
    private ArrayList<VariableNode> procedureArguements;
    private String id;
    /**
     * auto generated getter
     * @return 
     */
    public ArrayList<VariableNode> getProcedureArguements() {
        return procedureArguements;
    }
    public String getId() {
        return id;
    }
    /**
     * auto generated setter
     * @param procedureArguements 
     */
    public void setProcedureArguements(ArrayList<VariableNode> procedureArguements) {
        this.procedureArguements = procedureArguements;
    }
    public void setID(String id) {
        this.id = id;
    }
    /**
     * constructors for procedure node 
     * @param procedureArguements 
     */
    public ProcedureNode(ArrayList<VariableNode> procedureArguements, String id) {
        this.procedureArguements = procedureArguements;
        this.id = id;
    }
    public ProcedureNode() {
        this.procedureArguements = null;
        this.id = "";
    }
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "ProcedureNode: " + this.id + "\n";
        for(VariableNode vn:this.procedureArguements){
            answer += vn.indentedToString(level + 1);
        }
        return(answer);
    }
}
