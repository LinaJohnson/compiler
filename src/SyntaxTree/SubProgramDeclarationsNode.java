package SyntaxTree;

import java.util.ArrayList;

/**
 *
 * @author LinaVo
 */
public class SubProgramDeclarationsNode extends SyntaxTreeNode {
    
    // instantiation variables
    ArrayList<SubProgramNode> procs;

    /**
     * auto generated getter
     * @return 
     */
    public ArrayList<SubProgramNode> getProcs() {
        return procs;
    }
    
    /**
     * auto generated setter
     * @param procs 
     */
    public void setProcs(ArrayList<SubProgramNode> procs) {
        this.procs = procs;
    }
    
    /**
     * constructors
     * @param procs 
     */
    public SubProgramDeclarationsNode(ArrayList<SubProgramNode> procs) {
        this.procs = procs;
    }
    public SubProgramDeclarationsNode() {
        this.procs = procs;
    }
    
    public void addAll(SubProgramDeclarationsNode procs) {
        this.procs.addAll(procs.getProcs());
    }
    public void add(SubProgramNode node) {
        this.procs.add(node);
    }
    
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "SubprogramDeclarationsNode\n";
        if(this.procs != null) {
            for(SubProgramNode sn: this.procs) {
                answer += sn.indentedToString(level + 1);
            }
        }
        return(answer);
    }
    
}
