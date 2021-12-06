<<<<<<< HEAD
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class myGraghP extends JFrame {
    int width = Toolkit.getDefaultToolkit().getScreenSize().width/2 ;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height /2;
    double xmin = Double.MAX_VALUE;
    double xmax = Double.MIN_VALUE;
    double ymin = Double.MAX_VALUE;
    double ymax = Double.MIN_VALUE;


    public myGraghP(ArrayList<Nodes> test2) throws HeadlessException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenSize.width/2  , screenSize.height/2);
        this.add(new mypanel(test2));
        this.setTitle("gal and avidan");
        this.setVisible(true);
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

            for (int i = 0; i < test2.size(); i++) {
                Point_3D temp_p = new Point_3D(test2.get(i).getLocation().x(), test2.get(i).getLocation().y(), test2.get(i).getLocation().z());
                Nodes temp_n = new Nodes(temp_p, i);
                this.a.add(temp_n);
                for (int j = 0; j < test2.get(i).getEdgeList().size(); j++) {
                    Edges temp_e = new Edges(test2.get(i).getEdgeList().get(j).getSrc(), test2.get(i).getEdgeList().get(j).getWeight(), test2.get(i).getEdgeList().get(j).getDest());
                    this.a.get(i).getEdgeList().add(temp_e);
                }

            }


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
                g.drawString("" + this.a.get(i).getid(), (int) scalex(temp_p.x()), (int) scaley(temp_p.y()));

                if (this.a.get(i).getEdgeList().size() != 0) {

                    int src = i;
                    int dest = 0;
                    for (int j = 0; j < this.a.get(i).getEdgeList().size(); j++) {

                        dest = this.a.get(src).getEdgeList().get(j).getDest();
                        prev = this.a.get(dest).getLocation();
                        double wightlocx = (prev.x()-temp_p.x()) * 0.3;

                        double wightlocy=(prev.y()- temp_p.y()) * 0.3;
                        g.setColor(Color.black);
                        int num3afterpoint=(int)((this.a.get(i).getEdgeList().get(j).getWeight()-((int)this.a.get(i).getEdgeList().get(j).getWeight()))*1000);
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
}
=======
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class myGraghP extends JFrame {
    int width = Toolkit.getDefaultToolkit().getScreenSize().width/2 ;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height /2;
    double xmin = Double.MAX_VALUE;
    double xmax = Double.MIN_VALUE;
    double ymin = Double.MAX_VALUE;
    double ymax = Double.MIN_VALUE;


    public myGraghP(ArrayList<Nodes> test2) throws HeadlessException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenSize.width/2  , screenSize.height/2);
        this.add(new mypanel(test2));
        this.setTitle("gal and avidan");
        this.setVisible(true);
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

            for (int i = 0; i < test2.size(); i++) {
                Point_3D temp_p = new Point_3D(test2.get(i).getLocation().x(), test2.get(i).getLocation().y(), test2.get(i).getLocation().z());
                Nodes temp_n = new Nodes(temp_p, i);
                this.a.add(temp_n);
                for (int j = 0; j < test2.get(i).getEdgeList().size(); j++) {
                    Edges temp_e = new Edges(test2.get(i).getEdgeList().get(j).getSrc(), test2.get(i).getEdgeList().get(j).getWeight(), test2.get(i).getEdgeList().get(j).getDest());
                    this.a.get(i).getEdgeList().add(temp_e);
                }

            }


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
                g.drawString("" + this.a.get(i).getid(), (int) scalex(temp_p.x()), (int) scaley(temp_p.y()));

                if (this.a.get(i).getEdgeList().size() != 0) {

                    int src = i;
                    int dest = 0;
                    for (int j = 0; j < this.a.get(i).getEdgeList().size(); j++) {

                        dest = this.a.get(src).getEdgeList().get(j).getDest();
                        prev = this.a.get(dest).getLocation();
                        double wightlocx = (prev.x()-temp_p.x()) * 0.3;

                        double wightlocy=(prev.y()- temp_p.y()) * 0.3;
                        g.setColor(Color.black);
                        int num3afterpoint=(int)((this.a.get(i).getEdgeList().get(j).getWeight()-((int)this.a.get(i).getEdgeList().get(j).getWeight()))*1000);
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
}
>>>>>>> 90fde0b813b3f1a1324d05b74d5f1b9f991db873
