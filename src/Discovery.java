import java.util.Vector;

//this class implements the Discovery Faze used at last by the udp class
public class Discovery {
    private String name;
    private String IP;
    private Vector<String[]> nodeList;

//Initializing a node with a preloaded List,usually taken from a file
    public Discovery(String name, String IP ,Vector<String[]> nodeList ){
        this.name=name;
        this.IP=IP;
        this.nodeList=nodeList;
    }
//Initializing a node without a preloaded list
    public Discovery(String name, String IP){
        this.name=name;
        this.IP=IP;
    }

    public Vector<String[]> getNodeList() {
        return nodeList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    //this function makes sure that a node's List doesnt contain its very own info
    public Vector<String[]> listCorrection(Vector<String[]> List){
        int i;
        Vector<String[]> CorrectedList = new Vector<>();
        CorrectedList = List;
        for (i=0;i<CorrectedList.size();i++){
            if(CorrectedList.get(i)[0] == this.name && CorrectedList.get(i)[1] == this.IP  ){
                CorrectedList.remove(i);
            }
        }
        return CorrectedList;
    }

    //while setting a new list, we make sure that the list is corrected first
    public void setNodeList(Vector<String[]> nodeList) {
        this.nodeList = listCorrection(nodeList);
    }
}
