package Gui;

import Algo.*;
import api.*;
import utils.Listener;
import utils.ResponsiveEditorTools;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.EventQueue.invokeLater;


public class Ex2 extends javax.swing.JFrame {

    private static GraphManager graphManager;
    private GeoLocation clickPosition = null;
    private NodeData firstSelectedNode = null;
    private NodeData secondSelectedNode = null;
    private MODE mode = MODE.NONE;
    private DrawArea drawArea;
    private final List<NodeData> selectedNodes = new ArrayList<>();

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGraph(String json_file) {
        graphManager = new GraphManager();
        DirectedWeightedGraph ans = null;
        try {
            graphManager.loadFromFile(json_file);
            ans = graphManager.getGraph();
        } catch (Exception e) {
            ans = new DWGraph();
            graphManager.setGraph((DWGraph) ans);
        }
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraph graph = getGraph(json_file);
        DirectedWeightedGraphAlgorithms ans = graphManager.getAlgorithm();
        return ans;
    }

    /**
     * This static function will run your GUI using the json file.
     *
     * @param json_file - a json file (e.g., G1.json - G3.json)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);

        invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ex2(alg).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

        DWGraphAlgorithm myGraph = new DWGraphAlgorithm();
        String json_file = "/Users/david/IdeaProjects/Graphs/data/G1.json";

        if (args.length != 0) {
            json_file = args[0];
        }
        runGUI(json_file);

    }


    /**
//     * Creates new form GUI
     *
     * @param algorithm
     */
    public Ex2(DirectedWeightedGraphAlgorithms algorithm) {
        initComponents();
        initDrawArea();

        graphManager.initDrawers(drawArea);
        graphManager.initAlgo(algorithm);
    }




    private void initComponents() {

        connectivityCheckDialog = new javax.swing.JDialog();
        footerConnectivity = new javax.swing.JPanel();
        okBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        messagePanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        shortestPathDistanceDialog = new javax.swing.JDialog();
        messageSPDPanel = new javax.swing.JPanel();
        spdLabel = new javax.swing.JLabel();
        shortestPathDistLabel = new javax.swing.JLabel();
        footerSPD = new javax.swing.JPanel();
        okSPDBtn = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        statusPanel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        statistics = new javax.swing.JPanel();
        status = new javax.swing.JLabel();
        editor = new javax.swing.JScrollPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        fileMenuSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        Node = new javax.swing.JMenu();
        addNodeMenuItem = new javax.swing.JMenuItem();
        removeNodeMenuItem = new javax.swing.JMenuItem();
        Edge = new javax.swing.JMenu();
        addEdgeMenuItem = new javax.swing.JMenuItem();
        removeEdgeMenuItem = new javax.swing.JMenuItem();
        deleteGraphMenuItem = new javax.swing.JMenuItem();
        graphMenu = new javax.swing.JMenu();
        spMenuItem = new javax.swing.JMenuItem();
        spdMenuItem = new javax.swing.JMenuItem();
        tspMenu = new javax.swing.JMenu();
        selectTSPCitiesMenuItem = new javax.swing.JMenuItem();
        applyTSPMenuItem = new javax.swing.JMenuItem();
        isConnectedMenuItem = new javax.swing.JMenuItem();
        graphCenterMenuItem = new javax.swing.JMenuItem();

        connectivityCheckDialog.setTitle("Connectivity Check");
        connectivityCheckDialog.setMinimumSize(new java.awt.Dimension(320, 240));
        connectivityCheckDialog.setResizable(false);

        footerConnectivity.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        footerConnectivity.setLayout(new java.awt.BorderLayout());

        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        footerConnectivity.add(okBtn, java.awt.BorderLayout.LINE_END);
        footerConnectivity.add(filler1, java.awt.BorderLayout.CENTER);

        connectivityCheckDialog.getContentPane().add(footerConnectivity, java.awt.BorderLayout.PAGE_END);

        messagePanel.setLayout(new java.awt.BorderLayout());

        messageLabel.setFont(new java.awt.Font("Cantarell Extra Bold", 2, 18)); // NOI18N
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText("The graph is ");
        messageLabel.setPreferredSize(new java.awt.Dimension(256, 128));
        messagePanel.add(messageLabel, java.awt.BorderLayout.CENTER);

        connectivityCheckDialog.getContentPane().add(messagePanel, java.awt.BorderLayout.CENTER);

        shortestPathDistanceDialog.setTitle("Shortest Path Distance");
        shortestPathDistanceDialog.setMinimumSize(new java.awt.Dimension(320, 240));
        shortestPathDistanceDialog.setResizable(false);

        messageSPDPanel.setLayout(new java.awt.BorderLayout());

        spdLabel.setFont(new java.awt.Font("Cantarell Extra Bold", 2, 18)); // NOI18N
        spdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        spdLabel.setText("The shortest path distance is:");
        spdLabel.setPreferredSize(new java.awt.Dimension(256, 40));
        messageSPDPanel.add(spdLabel, java.awt.BorderLayout.NORTH);

        shortestPathDistLabel.setFont(new java.awt.Font("Comfortaa", 3, 24));
        shortestPathDistLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        shortestPathDistLabel.setText("0.0");
        shortestPathDistLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        shortestPathDistLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        shortestPathDistLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        shortestPathDistLabel.setMaximumSize(new java.awt.Dimension(500, 200));
        shortestPathDistLabel.setMinimumSize(new java.awt.Dimension(180, 60));
        shortestPathDistLabel.setName("");
        shortestPathDistLabel.setPreferredSize(new java.awt.Dimension(200, 100));
        messageSPDPanel.add(shortestPathDistLabel, java.awt.BorderLayout.CENTER);

        shortestPathDistanceDialog.getContentPane().add(messageSPDPanel, java.awt.BorderLayout.CENTER);

        footerSPD.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        footerSPD.setLayout(new java.awt.BorderLayout());

        okSPDBtn.setText("OK");
        okSPDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okSPDBtnActionPerformed(evt);
            }
        });
        footerSPD.add(okSPDBtn, java.awt.BorderLayout.LINE_END);
        footerSPD.add(filler2, java.awt.BorderLayout.CENTER);

        shortestPathDistanceDialog.getContentPane().add(footerSPD, java.awt.BorderLayout.PAGE_END);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GraphEditor");
        setBounds(new java.awt.Rectangle(0, 0, 640, 480));
        setPreferredSize(new java.awt.Dimension(1080, 720));
        setSize(new java.awt.Dimension(640, 480));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        statusPanel.setLayout(new java.awt.BorderLayout());

        label.setText("status:");
        statusPanel.add(label, java.awt.BorderLayout.LINE_START);
        statusPanel.add(statistics, java.awt.BorderLayout.LINE_END);
        statusPanel.add(status, java.awt.BorderLayout.CENTER);

        getContentPane().add(statusPanel, java.awt.BorderLayout.PAGE_END);

        editor.setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().add(editor, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        openMenuItem.setText("Open ...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveMenuItem.setText("Save ...");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);
        fileMenu.add(fileMenuSeparator);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        Node.setText("Nodes");

        addNodeMenuItem.setText("Add");
        addNodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNodeMenuItemActionPerformed(evt);
            }
        });
        Node.add(addNodeMenuItem);

        removeNodeMenuItem.setText("Remove");
        removeNodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeNodeMenuItemActionPerformed(evt);
            }
        });
        Node.add(removeNodeMenuItem);

        editMenu.add(Node);

        Edge.setText("Edges");

        addEdgeMenuItem.setText("Add");
        addEdgeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEdgeMenuItemActionPerformed(evt);
            }
        });
        Edge.add(addEdgeMenuItem);

        removeEdgeMenuItem.setText("Remove");
        removeEdgeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeEdgeMenuItemActionPerformed(evt);
            }
        });
        Edge.add(removeEdgeMenuItem);

        editMenu.add(Edge);

        deleteGraphMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        deleteGraphMenuItem.setText("Delete graph");
        deleteGraphMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGraphMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(deleteGraphMenuItem);

        menuBar.add(editMenu);

        graphMenu.setText("Graph");

        spMenuItem.setText("Shortest Path");
        spMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spMenuItemActionPerformed(evt);
            }
        });
        graphMenu.add(spMenuItem);

        spdMenuItem.setText("Shortest Path Distance");
        spdMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spdMenuItemActionPerformed(evt);
            }
        });
        graphMenu.add(spdMenuItem);

        tspMenu.setText("TSP");

        selectTSPCitiesMenuItem.setText("Select cities");
        selectTSPCitiesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTSPCitiesMenuItemActionPerformed(evt);
            }
        });
        tspMenu.add(selectTSPCitiesMenuItem);

        applyTSPMenuItem.setText("Apply TSP");
        applyTSPMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyTSPMenuItemActionPerformed(evt);
            }
        });
        tspMenu.add(applyTSPMenuItem);

        graphMenu.add(tspMenu);

        isConnectedMenuItem.setText("Check Connectivty");
        isConnectedMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isConnectedMenuItemActionPerformed(evt);
            }
        });
        graphMenu.add(isConnectedMenuItem);

        graphCenterMenuItem.setText("Graph Center");
        graphCenterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphCenterMenuItemActionPerformed(evt);
            }
        });
        graphMenu.add(graphCenterMenuItem);

        menuBar.add(graphMenu);

        setJMenuBar(menuBar);

        pack();
    }

    private void initDrawArea() {
        drawArea = new DrawArea();
        drawArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawAreaMouseClicked(evt);
            }
        });
        drawArea.setLayout(null);
        editor.setViewportView(drawArea);
    }

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        boolean loaded = graphManager.load();
        if (loaded) {
            graphManager.setGraph(ResponsiveEditorTools.scaleGraphToEditor(graphManager.getGraph(), this.getSize()));
            setStatus(MODE.NONE, "Graph loaded successfully", Color.blue);
        } else {
            setStatus(MODE.NONE, "Graph loading failed", Color.red);
        }
    }

    private void addNodeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        setStatus(MODE.ADD_NODE, "Click on the whiteboard to add a node", Color.black);
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void spMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        setStatus(MODE.SHORTEST_PATH, "Select two nodes to compute the shortest path between them", Color.BLACK);
    }

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            graphManager.save();

        } catch (Exception e) {
            setStatus(MODE.NONE, "File save failed", Color.red);
        }
    }

    private void removeNodeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        setStatus(MODE.REMOVE_NODE, "Click on a node to remove it", Color.black);
    }

    private void addEdgeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        setStatus(MODE.ADD_EDGE, "Select two nodes to connect to each other", Color.black);
    }

    private void removeEdgeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        setStatus(MODE.REMOVE_EDGE, "Select the edge's nodes to remove it", Color.black);
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        if (graphManager.getGraph() != null)
            graphManager.setGraph(ResponsiveEditorTools.scaleGraphToEditor(graphManager.getGraph(), this.getSize()));
    }

    private void deleteGraphMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        graphManager.deleteGraph();
        setStatus(MODE.NONE, "Graph deleted successfully", Color.red);
    }

    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {
        connectivityCheckDialog.dispose();
    }

    private void isConnectedMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        String message;
        boolean isConnected = graphManager.isConnected();
        if (isConnected) {
            message = "This graph is connected";
        } else {
            message = "This graph is not connected";
        }
        messageLabel.setText(message);
        connectivityCheckDialog.setVisible(true);
    }

    private void okSPDBtnActionPerformed(java.awt.event.ActionEvent evt) {
        shortestPathDistanceDialog.dispose();
    }

    private void spdMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        setStatus(MODE.SHORTEST_PATH_DIST, "Select the source and destination nodes to compute shortest distance between them", Color.BLACK);
    }

    private void graphCenterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        Node node = (Node) graphManager.getGraphCenter();
        if (node != null) {
            setStatus(MODE.NONE, "The graph center is represented by the node holding the key: " + node.getKey(), Color.blue);
        } else {
            setStatus(MODE.NONE, "The graph is not connected, so there is no center", Color.red);
        }
    }

    private void selectTSPCitiesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        selectedNodes.clear();
        setStatus(MODE.TSP, "Select the cities to traverse", Color.BLACK);
    }
    private void applyTSPMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        List<NodeData> path = graphManager.tsp(selectedNodes);
        graphManager.renderPath(path);
        selectedNodes.clear();
        resetStatus();
    }

    private void drawAreaMouseClicked(java.awt.event.MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        clickPosition = new Location(x, y, 0);
        if (null != mode) {
            switch (mode) {
                case ADD_NODE:
                    String pos = x + "," + y + ",0.0";
                    int generatedKey = graphManager.getGeneratedKey();
                    NodeData node = new Node(generatedKey, pos);
                    graphManager.addNode(node);
                    resetStatus();
                    break;
                case REMOVE_NODE:
                    int nodeId = graphManager.getIdFromCoordinates(clickPosition);
                    if (nodeId != -1) {
                        graphManager.removeNode(nodeId);
                    }
                    resetStatus();
                    break;
                case ADD_EDGE:
                    if (firstSelectedNode == null) {
                        int firstNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (firstNodeId != -1) {
                            firstSelectedNode = graphManager.getNode(firstNodeId);
                        }
                    } else {
                        int secondNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (secondNodeId != -1) {
                            secondSelectedNode = graphManager.getNode(secondNodeId);
                            EdgeWeightEditor edgeWeightEditor = new EdgeWeightEditor(this, true);
                            edgeWeightEditor.setOnSave(new Listener() {
                                @Override
                                public void onAction(double weight) {
                                    try {
                                        EdgeData edge = new EData(firstSelectedNode.getKey(), secondSelectedNode.getKey(), weight);
                                        graphManager.addEdge(edge);
                                        resetStatus();
                                    } catch (Exception e) {
                                        setStatus(MODE.NONE, e.getMessage(), Color.red);
                                    } finally {
                                        clearSelectedNodes();
                                    }
                                }
                            });
                            edgeWeightEditor.setVisible(true);
                        }
                    }
                    break;
                case REMOVE_EDGE:
                    if (firstSelectedNode == null) {
                        int firstNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (firstNodeId != -1) {
                            firstSelectedNode = graphManager.getNode(firstNodeId);
                        }
                    } else {
                        int secondNodeId = graphManager.getIdFromCoordinates(clickPosition);

                        if (secondNodeId != -1) {
                            if (graphManager.removeEdge(firstSelectedNode.getKey(), secondNodeId)) {
                                setStatus(MODE.NONE, "Edge removed successfully between " + firstSelectedNode.getKey() + " and " + secondNodeId, Color.blue);
                            } else {
                                setStatus(MODE.NONE, "Edge does not exists between " + firstSelectedNode.getKey() + " and " + secondNodeId, Color.red);
                            }
                            clearSelectedNodes();
                        }
                    }
                    break;
                case SHORTEST_PATH_DIST:

                    if (firstSelectedNode == null) {
                        int firstNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (firstNodeId != -1) {
                            firstSelectedNode = graphManager.getNode(firstNodeId);
                        }
                    } else {
                        int secondNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (secondNodeId != -1) {
                            double distance = graphManager.shortestPathDist(firstSelectedNode.getKey(), secondNodeId);
                            if (distance == -1) {
                                shortestPathDistLabel.setText("From "+ firstSelectedNode.getKey()+" to "+ secondNodeId+ " Infinity");
                            } else {
                                shortestPathDistLabel.setText("From "+ firstSelectedNode.getKey()+" to "+ secondNodeId + ": " +Double.toString(distance));
                            }
                            shortestPathDistanceDialog.setVisible(true);
                            clearSelectedNodes();
                            resetStatus();
                        }
                    }

                    break;
                case SHORTEST_PATH:
                    if (firstSelectedNode == null) {
                        int firstNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (firstNodeId != -1) {
                            firstSelectedNode = graphManager.getNode(firstNodeId);
                        }
                    } else {
                        int secondNodeId = graphManager.getIdFromCoordinates(clickPosition);
                        if (secondNodeId != -1) {
                            try {
                                List<NodeData> path = graphManager.shortestPath(firstSelectedNode.getKey(), secondNodeId);
                                graphManager.renderPath(path);

                            } catch (Exception e) {
                                setStatus(MODE.NONE, "There is no path between the selected nodes!", Color.red);
                            }
                            clearSelectedNodes();
                        }
                    }
                    break;
                case TSP:
                    int nodeID = graphManager.getIdFromCoordinates(clickPosition);
                    if (nodeID != -1) {
                        NodeData selectedNode = graphManager.getNode(nodeID);
                        selectedNodes.add(selectedNode);
                        StringBuilder selectedNodesString = new StringBuilder("Selected Nodes: ");
                        selectedNodes.forEach(selected->{
                            selectedNodesString.append(selected.getKey()).append(", ");

                        });
                        setStatus(MODE.TSP, selectedNodesString.toString(),Color.black);
                    }
                    break;
                default:
                    setStatus(MODE.NONE, "Select an option to perform from the menus above", Color.black);
                    break;
            }

        }
    }

    private void clearSelectedNodes() {
        firstSelectedNode = null;
        secondSelectedNode = null;
    }

    private void setStatus(MODE mode, String status, Color color) {
        this.status.setForeground(color);
        this.status.setText(status);
        this.mode = mode;
    }

    private void resetStatus() {
        status.setForeground(Color.BLACK);
        status.setText("Select an option to perform from the menus above");
        mode = MODE.NONE;
    }
    // Variables declaration
    private javax.swing.JMenu Edge;
    private javax.swing.JMenu Node;
    private javax.swing.JMenuItem addEdgeMenuItem;
    private javax.swing.JMenuItem addNodeMenuItem;
    private javax.swing.JMenuItem applyTSPMenuItem;
    private javax.swing.JDialog connectivityCheckDialog;
    private javax.swing.JMenuItem deleteGraphMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JScrollPane editor;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JPanel footerConnectivity;
    private javax.swing.JPanel footerSPD;
    private javax.swing.JMenuItem graphCenterMenuItem;
    private javax.swing.JMenu graphMenu;
    private javax.swing.JMenuItem isConnectedMenuItem;
    private javax.swing.JLabel label;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JPanel messagePanel;
    private javax.swing.JPanel messageSPDPanel;
    private javax.swing.JButton okBtn;
    private javax.swing.JButton okSPDBtn;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem removeEdgeMenuItem;
    private javax.swing.JMenuItem removeNodeMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem selectTSPCitiesMenuItem;
    private javax.swing.JLabel shortestPathDistLabel;
    private javax.swing.JDialog shortestPathDistanceDialog;
    private javax.swing.JMenuItem spMenuItem;
    private javax.swing.JLabel spdLabel;
    private javax.swing.JMenuItem spdMenuItem;
    private javax.swing.JPanel statistics;
    private javax.swing.JLabel status;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenu tspMenu;


}
