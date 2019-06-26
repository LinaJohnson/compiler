package SyntaxTree;

import java.util.ArrayList;

/**
 * @author LinaVo
 */
public abstract class SubProgramNode extends SyntaxTreeNode {
    private String id; 
    private ArrayList<VariableNode> arguements;
    private ArrayList<DeclarationsNode> variables;
    private ArrayList<SubProgramDeclarationsNode> subprogramDecs;
    private CompoundStatementNode compoundStmts; 

    public String getId() {
        return id;
    }

    public ArrayList<VariableNode> getArguements() {
        return arguements;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArguements(ArrayList<VariableNode> arguements) {
        this.arguements = arguements;
    }
    
    public void setVariables(ArrayList<DeclarationsNode> variables) {
        this.variables = variables;
    }
    
    public void setSubProgramDecs(ArrayList<SubProgramDeclarationsNode> subprogramDecs) {
        this.subprogramDecs = subprogramDecs;
    }
    
    public void setCompoundStmts(CompoundStatementNode compoundStmts) {
        this.compoundStmts = compoundStmts;
    }
}
