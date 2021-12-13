import api.DirectedWeightedGraphAlgorithms;
import api.GeoLocation;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class myGraghP  implements ActionListener, MouseListener {
    int width = Toolkit.getDefaultToolkit().getScreenSize().width/2 ;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height /2;
    ArrayList<Nodes> test2=new ArrayList<>();
    double xmin = Double.MAX_VALUE;
    double xmax = Double.MIN_VALUE;
    double ymin = Double.MAX_VALUE;
    double ymax = Double.MIN_VALUE;
    JFrame j=new JFrame();
    Menu file=new Menu("File");
    MenuItem load=new MenuItem("Load");
    private DWG Jgraph;
    private DirectedWeightedGraphAlgorithms AalgoGragh=new DWGAlgo();
    String file_name="";
    //load.addActionListener(this);
    //   file.add(load);
    MenuItem deleteNode=new MenuItem("Delete point");
    //print.addActionListener(this);
    //file.add(print);
    MenuItem addNode=new MenuItem("Add point");
    MenuItem delete_edge =new MenuItem("Delete edge");
    MenuItem save=new MenuItem("Save");
    //save.addActionListener(this);
    // file.add(save);

    //edit.addActionListener(this);
    //   file.add(edit);
    Menu Algo=new Menu("Algo");
    MenuItem shortestPathDist=new MenuItem("shortestPathDist");
    //shortestPathDist.addActionListener(this);
    //Algo.add(shortestPathDist);
    MenuItem shortestPath=new MenuItem("shortestPath");
    //shortestPath.addActionListener(this);
    //Algo.add(shortestPath);
    MenuItem connect=new MenuItem("connect");
    //connect.addActionListener(this);
    //Algo.add(connect);
    MenuItem isConnected=new MenuItem("isConnect");
    //isConnected.addActionListener(this);
    //Algo.add(isConnected);
    MenuItem center=new MenuItem("center");
    //center.addActionListener(this.j);
    //Algo.add(center);
    MenuItem tsp=new MenuItem("tsp");
    // tsp.addActionListener(this);
    //Algo.add(tsp);


    MenuBar menubar=new MenuBar();


    public myGraghP(ArrayList<Nodes> test2,String path) throws HeadlessException {

//         this.L=new JLabel();
//        L.setBounds(10,10,width,height-100);
//        ImageIcon icon=new ImageIcon("./pic/football.jpg");
//        L.setIcon(icon);
//        L.setVisible(false);
//        this.add(L);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.j.setSize(screenSize.width/2  , screenSize.height/2);

        // Menu file=new Menu("File");
        //MenuItem load=new MenuItem("Load");
        load.addActionListener(this);
        file.add(load);
        //MenuItem print=new MenuItem("print");
        deleteNode.addActionListener(this);
        Algo.add(deleteNode);
        addNode.addActionListener(this);
        Algo.add(addNode);
        //MenuItem save=new MenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        //MenuItem edit=new MenuItem("Edit");
        delete_edge.addActionListener(this);
        Algo.add(delete_edge);
        //Menu Algo=new Menu("Algo");
        //MenuItem shortestPathDist=new MenuItem("shortestPathDist");
        shortestPathDist.addActionListener(this);
        Algo.add(shortestPathDist);
        //MenuItem shortestPath=new MenuItem("shortestPath");
        shortestPath.addActionListener(this);
        Algo.add(shortestPath);
        //MenuItem connect=new MenuItem("connect");
        connect.addActionListener(this);
        Algo.add(connect);
        //MenuItem isConnected=new MenuItem("isConnect");
        isConnected.addActionListener(this);
        Algo.add(isConnected);
        //MenuItem center=new MenuItem("center");
        center.addActionListener(this);
        Algo.add(center);
        // MenuItem tsp=new MenuItem("tsp");
        tsp.addActionListener(this);
        Algo.add(tsp);


        MenuBar menubar=new MenuBar();
        menubar.add(file);
        menubar.add(Algo);
        this.j.setMenuBar(menubar);
        //my_panel.mypanel panel=new my_panel.mypanel();
        //this.j.add(new mypanel(this.test2));
        this.j.setTitle("gal and avidan");
        this.j.setVisible(true);
        this.j.addMouseListener(this);
        this.j.add(new mypanel(test2));
        this.Jgraph=new DWG(test2);
        this.file_name=path;
        if(!this.file_name.isEmpty()) {
            this.AalgoGragh.load(this.file_name);

        }
        setvalue(test2);


    }
    public void setvalue(ArrayList<Nodes> a) {
        double xmin = Double.MAX_VALUE;
        double xmax = Double.MIN_VALUE;
        double ymin = Double.MAX_VALUE;
        double ymax = Double.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getLocation().x() > xmax) {
                xmax = a.get(i).getLocation().x();
            }
            if (a.get(i).getLocation().x() < xmin) {
                xmin = a.get(i).getLocation().x();
            }

            if (a.get(i).getLocation().y() > ymax) {
                ymax = a.get(i).getLocation().y();
            }
            if (a.get(i).getLocation().y() < ymin) {
                ymin = a.get(i).getLocation().y();
            }


        }
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymin = ymin;
        this.ymax = ymax;

    }

    public class mypanel extends JPanel {
        ArrayList<Nodes> a = new ArrayList<>();


        /**
         * Creates a new <code>JPanel</code> with a double buffer
         * and a flow layout.
         */
        public mypanel(ArrayList<Nodes> test2) {

            this.a = test2;


//            for (int i = 0; i < test2.size(); i++) {
//
//                Point_3D temp_p = new Point_3D(test2.get(i).getLocation().x(), test2.get(i).getLocation().y(), test2.get(i).getLocation().z());
//                Nodes temp_n = new Nodes(temp_p, i);
//                this.a.add(temp_n);
//                for (int key : test2.get(i).getEdgeMap().keySet()) {
//                    int source = test2.get(i).getEdgeMap().get(key).getSrc();
//                    double weight = test2.get(i).getEdgeMap().get(key).getWeight();
//                    int destination = test2.get(i).getEdgeMap().get(key).getDest();
//                    Edges temp_e = new Edges(source,weight, destination);
//                    this.a.get(i).getEdgeMap().put(test2.get(i).getEdgeMap().get(key).getSrc(),temp_e);
//                }
//
//            }


        }

        private double scalex(double x) {
            return (width * ((x - xmin) / (xmax - xmin)) / 1.5) + 20;
        }

        private double scaley(double y) {
            return ((height * (y - ymin) / (ymax - ymin)) / 1.5) + 20;
        }



        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Point_3D prev = new Point_3D(0, 0, 0);
//            for (int i = 0; i < this.a.size(); i++) {
            for (Nodes node:this.a) {
                int i=node.getKey();

                g.setColor(Color.BLACK);
                Point_3D temp_p = new Point_3D(this.a.get(i).getLocation().x(), this.a.get(i).getLocation().y(), this.a.get(i).getLocation().z());
                g.fillOval((int) scalex(temp_p.x()), (int) scaley(temp_p.y()), 10, 10);
                g.drawString("" + this.a.get(i).getKey(), (int) scalex(temp_p.x()), (int) scaley(temp_p.y()));

                if (this.a.get(i).getEdgeMap().size() != 0) {

                    int src = i;
                    int dest = 0;
//                    for (int j = 0; j < this.a.get(i).getEdgeMap().size(); j++) {
                    for (int key : this.a.get(i).getEdgeMap().keySet()) {


                        dest = this.a.get(src).getEdgeMap().get(key).getDest();
                        prev = this.a.get(dest).getLocation();
                        double wightlocx = (prev.x()-temp_p.x()) * 0.3;

                        double wightlocy=(prev.y()- temp_p.y()) * 0.3;
                        g.setColor(Color.black);
                        int num3afterpoint=(int)((this.a.get(i).getEdgeMap().get(key).getWeight()-((int)this.a.get(i).getEdgeMap().get(key).getWeight()))*1000);
                        // g.drawString(""+(int)this.a.get(i).getEdgeList().get(j).getWeight()+"."+num3afterpoint, (int)(scalex(temp_p.x()+wightlocx))-3,(int)(scaley(temp_p.y()+wightlocy))+3);
                        g.setColor(Color.green);
                        if (temp_p.x() != prev.x()) {

                            double xvec = (prev.x()-temp_p.x()) * 0.9;
                            double yvec = (prev.y()- temp_p.y()) * 0.9;

                            g.fillOval((int) (scalex(xvec + temp_p.x() )+3), (int) (scaley(yvec + temp_p.y())+3 ), 6, 6);

                            g.setColor(Color.blue);
                            g.drawLine((int) scalex(temp_p.x()) + 5, (int) scaley(temp_p.y()) + 5, (int) scalex(prev.x()) + 5, (int) scaley(prev.y()) + 5);
                        }
                    }

                }
            }


        }
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String str =e.getActionCommand();
        if(str=="Load") {
            int ans;
            JFileChooser path = new JFileChooser();
            ans = path.showOpenDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION) {
                File file = new File(path.getSelectedFile().getAbsolutePath());
                String file_name = String.valueOf((file));



                try {
                    ArrayList[] array_graph = jsonToGraph(file_name);
                    DWG testgraph = new DWG(array_graph[0],array_graph[1]);
                    this.Jgraph = testgraph;
                    this.j.dispose();
                    new myGraghP(testgraph.getNodeList(),file_name);


                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException | java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                //  this.file_name=file_name;
                this.AalgoGragh.load(file_name);
                //this.L.setVisible(true);

            }
        }
        if(str=="Save"){
            String fileJsonName=JOptionPane.showInputDialog(null,"Enter file name:");
            try {
                Boolean ans;
                System.out.println("save");
                ans = this.AalgoGragh.save("data/" + fileJsonName + ".json");
                JOptionPane.showMessageDialog(null, "The file was saved successfully");//successful
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "saved fail");
                System.out.println(fileJsonName);
            }
        }



        if(str=="shortestPath"){
            String inputSrc=JOptionPane.showInputDialog(null,"Enter src:");
            String inputDest=JOptionPane.showInputDialog(null,"Enter dest:");

            try{
                int src = Integer.parseInt(inputSrc);
                int dest = Integer.parseInt(inputDest);

                List<NodeData> shortlist = this.AalgoGragh.shortestPath(src, dest);
                String ans="";
                for (int i = 0; i <shortlist.size() ; i++) {
                    ans=ans+shortlist.get(i).getKey();
                    if(i!=shortlist.size()-1) {
                        ans = ans + "-->";
                    }
                }

                JOptionPane.showMessageDialog(null, "The Shortest path  from " + src + " to " + dest + " is: "+ans );
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Invalid Input");
            }


        }
        if(str=="shortestPathDist"){
            String inputSrc=JOptionPane.showInputDialog(null,"Enter src:");
            String inputDest=JOptionPane.showInputDialog(null,"Enter dest:");

            try{
                int src = Integer.parseInt(inputSrc);
                int dest = Integer.parseInt(inputDest);

                double path_dist = this.AalgoGragh.shortestPathDist(src, dest);

                JOptionPane.showMessageDialog(null, "The Shortest Distance from " + src + " to " + dest + " is: " + path_dist);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "pleas load gragh /has no path");
            }

        }
        if(str=="connect"){
            String inputSrc=JOptionPane.showInputDialog(null,"Enter src:");
            String inputDest=JOptionPane.showInputDialog(null,"Enter dest:");
            String inputWight=JOptionPane.showInputDialog(null,"Enter weight:");
            try{
                int src = Integer.parseInt(inputSrc);
                int dest = Integer.parseInt(inputDest);
                double weight=Double.parseDouble(inputWight);
                boolean ans;
                this.AalgoGragh.getGraph().connect(src, dest,weight);

                ans=this.AalgoGragh.save("data/myGraghChange.json");

                ArrayList[] array_graph = jsonToGraph("data/myGraghChange.json");
                DWG testgraph = new DWG(array_graph[0],array_graph[1]);
                this.Jgraph = testgraph;
                this.j.dispose();
                new myGraghP(testgraph.getNodeList(),"data/myGraghChange.json");


            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "connect failed");//failed
            }


        }
        if(str=="isConnect"){
            try{
                boolean ans=this.AalgoGragh.isConnected();
                JOptionPane.showMessageDialog(null,ans);
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"isconnect failed");
            }

        }
        if(str=="center"){
            try{
                NodeData temp;
                temp=this.AalgoGragh.center();

                JOptionPane.showMessageDialog(null,"The center ID point is: "+temp.getKey());
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"center failed");

            }


        }
        if(str=="tsp"){
            System.out.println("tsp");

        }
        if(str=="Delete edge"){
            String idpoint_src=JOptionPane.showInputDialog(null,"Enter Edge src:");
            String idpoint_dest=JOptionPane.showInputDialog(null,"Enter Edge dest :");
            try{
                int src=Integer.parseInt(idpoint_src);
                int dest=Integer.parseInt(idpoint_dest);
                this.AalgoGragh.getGraph().removeEdge(src,dest);
                boolean ans;
                ans = this.AalgoGragh.save("myGraghChange.json");

                ArrayList[] array_graph = jsonToGraph("myGraghChange.json");
                DWG testgraph = new DWG(array_graph[0], array_graph[1]);

                this.Jgraph = testgraph;
                this.j.dispose();
                new myGraghP(testgraph.getNodeList(), "myGraghChange.json");
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"delete edge failed");
            }

        }
        if(str=="Delete point"){
            String inputid=JOptionPane.showInputDialog(null,"Enter point ID:");


            int idp=Integer.parseInt(inputid);


            try{
                if(idp<this.AalgoGragh.getGraph().nodeSize()||idp>=0) {

                    this.AalgoGragh.getGraph().removeNode(idp);

                    boolean ans;
                    ans = this.AalgoGragh.save("myGraghChange.json");

                    ArrayList[] array_graph = jsonToGraph("myGraghChange.json");
                    DWG testgraph = new DWG(array_graph[0], array_graph[1]);

                    this.Jgraph = testgraph;
                    this.j.dispose();
                    new myGraghP(testgraph.getNodeList(), "myGraghChange.json");

                }


            }catch (Exception exception){
                if(idp>this.AalgoGragh.getGraph().nodeSize()||idp<0) {
                    JOptionPane.showMessageDialog(null, "This point does not exist");

                }else {
                    JOptionPane.showMessageDialog(null,"pleas load gragh");
                }
            }

        }
        if(str=="Add point"){
            String inputx=JOptionPane.showInputDialog(null,"Enter x:");
            String inputy=JOptionPane.showInputDialog(null,"Enter y:");

            try{
                double x = Double.parseDouble(inputx);
                double y = Double.parseDouble(inputy);
                Point_3D g=new Point_3D(x,y,0);
                NodeData temp=new Nodes(g,this.AalgoGragh.getGraph().nodeSize());


                boolean ans;
                this.AalgoGragh.getGraph().addNode(temp);

                ans=this.AalgoGragh.save("myGraghChange.json");

                ArrayList[] array_graph = jsonToGraph("data/myGraghChange.json");
                DWG testgraph = new DWG(array_graph[0],array_graph[1]);
                this.Jgraph = testgraph;
                this.j.dispose();
                new myGraghP(testgraph.getNodeList(),"/myGraghChange.json");


            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Add point failed");//failed
            }

        }
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    private ArrayList[] jsonToGraph(String jsonPath) throws IOException, java.text.ParseException, org.json.simple.parser.ParseException {
        ArrayList[] list_graph = new ArrayList[2];
        JSONParser jsonParse = new JSONParser();
        FileReader reader = new FileReader(jsonPath);
        Object obj = jsonParse.parse(reader);
        JSONObject graphJson = (JSONObject) obj;
        ArrayList<Nodes> node_list = new ArrayList<>();
        ArrayList<Edges> edge_list = new ArrayList<>();
        JSONArray Edges_array = (JSONArray) graphJson.get("Edges");
        for (int i = 0; i < Edges_array.size(); i++) {
            JSONObject edgeElement = (JSONObject) Edges_array.get(i);
            int srcE = Math.toIntExact((long) edgeElement.get("src"));
            double wE = (double) edgeElement.get("w");
            int destE = Math.toIntExact((long) edgeElement.get("dest"));
            edge_list.add(new Edges(srcE, wE, destE));

        }
        JSONArray Nodes_array = (JSONArray) graphJson.get("Nodes");
        for (int i = 0; i < Nodes_array.size(); i++) {
            JSONObject nodeElement = (JSONObject) Nodes_array.get(i);
            String pos = (String) nodeElement.get("pos");
            String[] posData = pos.split(",", 0);
            Point_3D new_point = new Point_3D(Double.parseDouble(posData[0]), Double.parseDouble(posData[1]),
                    Double.parseDouble(posData[2]));
            node_list.add(new Nodes(new_point, Math.toIntExact((long) nodeElement.get("id"))));
        }
        list_graph[0] = node_list;
        list_graph[1] = edge_list;
        return list_graph;
    }

}
