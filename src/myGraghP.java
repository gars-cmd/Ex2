import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


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
        //load.addActionListener(this);
          //   file.add(load);
    MenuItem print=new MenuItem("print");
        //print.addActionListener(this);
        //file.add(print);
    MenuItem save=new MenuItem("Save");
        //save.addActionListener(this);
            // file.add(save);
    MenuItem edit=new MenuItem("Edit");
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


    public myGraghP(ArrayList<Nodes> test2) throws HeadlessException {

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
        print.addActionListener(this);
        file.add(print);
        //MenuItem save=new MenuItem("Save");
        save.addActionListener(this);
             file.add(save);
        //MenuItem edit=new MenuItem("Edit");
        edit.addActionListener(this);
             file.add(edit);
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
            for (int i = 0; i < this.a.size(); i++) {
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


                test t = new test();
                try {
                    ArrayList[] array_graph = t.jsonToGraph(file_name);
                    DWG testgraph = new DWG(array_graph[0],array_graph[1]);
                    new myGraghP(testgraph.getNodeList());
//                    ArrayList<Nodes> arrNodes = new ArrayList<>();
//                    arrNodes = array_graph[0];
//                    ArrayList<Edges> arrEdges = new ArrayList<>();
//                    arrEdges = array_graph[1];
//                    for (int i = 0; i < arrNodes.size(); i++) {
////                        for (int j = 0; j < arrEdges.size(); j++) {
//                        for (int key : arrNodes.get(i).getEdgeMap().keySet()) {
//                            if (arrEdges.get(key).getSrc() == i) {
//                                arrNodes.get(i).getEdgeMap().put(arrEdges.get(key).getSrc(),arrEdges.get(key));
//
//                            }
//                        }
//                    }
//                    new myGraghP(arrNodes);




                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ParseException | java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                //this.L.setVisible(true);
                System.out.println("load action");
            }
        }
        if(str=="Save"){
            System.out.println("save");
        }
        if(str=="Edit"){
            System.out.println("Edit");

        }
        if(str=="shortestPath"){
            System.out.println("shortpath");

        }
        if(str=="shortestPathDist"){
            System.out.println("shortDist");
        }
        if(str=="connect"){
            System.out.println("connect");
        }
        if(str=="isConnect"){
            System.out.println("isConnect");
        }
        if(str=="center"){
            System.out.println("center");

        }
        if(str=="tsp"){
            System.out.println("tsp");

        }
        if(str=="print"){
           // my_panel.mypanel()
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



    }

