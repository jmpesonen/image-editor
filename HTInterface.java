
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Joni
 */
public class HTInterface extends javax.swing.JFrame {

    /**
     * Creates new form HTInterface
     */
    public HTInterface() {
        initComponents();
        addTextDialog.pack();
        settingsDialog.pack();
        scaleDialog.pack();
        exitDialog.pack();
        addTextDialog.setLocation(250, 250);
        scaleDialog.setLocation(250, 250);
        settingsDialog.setLocation(250, 250);
        imageComponent1.setLayout(null);
        undos.setLimit(10);
        logList.setModel(listModel);
        
        // Default exit action is DO_NOTHING; this handles closing with X
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                exitMenuItemActionPerformed(null);
            }
        });
        
        // All local fonts. If Arial doesn't exist, it defaults to first item in the list.
        JComboBox fonts;
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = new JComboBox(gEnv.getAvailableFontFamilyNames());
        settingsFontName.setModel(fonts.getModel());
        settingsFontName.setSelectedItem("Arial");
        // Font size 32
        settingsFontSize.setSelectedIndex(6);
        
        // These will be used to keep track of currently selected settings
        origFontName = (String)settingsFontName.getSelectedItem();
        origFontColor = new Color(0, 0, 0);
        origFontStyle = settingsFontStyle.getSelectedIndex();
        origFontSize = Integer.parseInt((String)settingsFontSize.getSelectedItem());
        origChangesStored = (int)settingsRecentChanges.getValue();
        scaleFactor = scaleSlider.getValue();
        
        // Small color preview panel in settings
        settingsFontColorPanel.setBackground(origFontColor);
        
        // Show only images and directories in file choosers
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        
        addTextDialog.getRootPane().setDefaultButton(addTextDialogButton);
        scaleDialog.getRootPane().setDefaultButton(scaleConfirmButton);
        exitDialog.getRootPane().setDefaultButton(exitDialogSaveButton);
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);
        
        // Shortcuts
        JComponent cp = (JComponent)getContentPane();
        InputMap inputMap = cp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        inputMap.put(KeyStroke.getKeyStroke("ctrl Z"), "undo");
        cp.getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undos.canUndo())
                    undos.undo();
            }    
        });
        
        inputMap.put(KeyStroke.getKeyStroke("ctrl Y"), "redo");
        cp.getActionMap().put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undos.canRedo())
                    undos.redo();
            }    
        });
        
        inputMap.put(KeyStroke.getKeyStroke("ctrl O"), "open");
        cp.getActionMap().put("open", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonActionPerformed(null);
            }    
        });
        
        inputMap.put(KeyStroke.getKeyStroke("ctrl S"), "save");
        cp.getActionMap().put("save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(null);
            }    
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addTextDialog = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        addableText = new javax.swing.JTextField();
        cancelAddTextButton = new javax.swing.JButton();
        addTextDialogButton = new javax.swing.JButton();
        settingsDialog = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        settingsFontName = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        settingsRecentChanges = new javax.swing.JSpinner();
        settingsCancelButton = new javax.swing.JButton();
        settingsSaveButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        settingsFontStyle = new javax.swing.JComboBox<>();
        settingsColorChooserButton = new javax.swing.JButton();
        settingsFontColorPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        settingsFontSize = new javax.swing.JComboBox<>();
        scaleDialog = new javax.swing.JDialog();
        scaleCancelButton = new javax.swing.JButton();
        scaleConfirmButton = new javax.swing.JButton();
        scaleSlider = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        exitDialog = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        exitDialogSaveButton = new javax.swing.JButton();
        exitDialogExitButton = new javax.swing.JButton();
        exitDialogCancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logList = new javax.swing.JList<>();
        scaleButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        addTextButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        imageComponent1 = new ImageComponent();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        settingsMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();

        jLabel4.setText("Add text:");

        cancelAddTextButton.setText("Cancel");
        cancelAddTextButton.setRequestFocusEnabled(false);
        cancelAddTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAddTextButtonActionPerformed(evt);
            }
        });

        addTextDialogButton.setText("Add");
        addTextDialogButton.setRequestFocusEnabled(false);
        addTextDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTextDialogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addTextDialogLayout = new javax.swing.GroupLayout(addTextDialog.getContentPane());
        addTextDialog.getContentPane().setLayout(addTextDialogLayout);
        addTextDialogLayout.setHorizontalGroup(
            addTextDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTextDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addTextDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTextDialogLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addableText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addTextDialogLayout.createSequentialGroup()
                        .addGap(0, 89, Short.MAX_VALUE)
                        .addComponent(addTextDialogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelAddTextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        addTextDialogLayout.setVerticalGroup(
            addTextDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTextDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addableText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addTextDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelAddTextButton)
                    .addComponent(addTextDialogButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Font name:");

        settingsFontName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Font color:");

        jLabel7.setText("Amount of recent changes stored:");

        settingsRecentChanges.setModel(new javax.swing.SpinnerNumberModel(10, 3, 15, 1));

        settingsCancelButton.setText("Cancel");
        settingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsCancelButtonActionPerformed(evt);
            }
        });

        settingsSaveButton.setText("Save changes");
        settingsSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsSaveButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Font style:");

        settingsFontStyle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plain", "Bold", "Italic", "Bold Italic" }));

        settingsColorChooserButton.setText("Open color chooser...");
        settingsColorChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsColorChooserButtonActionPerformed(evt);
            }
        });

        settingsFontColorPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout settingsFontColorPanelLayout = new javax.swing.GroupLayout(settingsFontColorPanel);
        settingsFontColorPanel.setLayout(settingsFontColorPanelLayout);
        settingsFontColorPanelLayout.setHorizontalGroup(
            settingsFontColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        settingsFontColorPanelLayout.setVerticalGroup(
            settingsFontColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jLabel11.setText("Font size:");

        settingsFontSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "12", "14", "16", "18", "24", "36", "48", "72" }));

        javax.swing.GroupLayout settingsDialogLayout = new javax.swing.GroupLayout(settingsDialog.getContentPane());
        settingsDialog.getContentPane().setLayout(settingsDialogLayout);
        settingsDialogLayout.setHorizontalGroup(
            settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(settingsSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(settingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(settingsDialogLayout.createSequentialGroup()
                        .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsDialogLayout.createSequentialGroup()
                                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(settingsFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(settingsFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(settingsDialogLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(settingsRecentChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(settingsDialogLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(settingsFontName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(settingsDialogLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(settingsFontColorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(settingsColorChooserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(74, Short.MAX_VALUE))))
        );
        settingsDialogLayout.setVerticalGroup(
            settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(settingsFontName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(settingsColorChooserButton, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6))
                    .addComponent(settingsFontColorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(settingsFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(settingsFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(settingsRecentChanges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsCancelButton)
                    .addComponent(settingsSaveButton))
                .addContainerGap())
        );

        scaleCancelButton.setText("Cancel");
        scaleCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleCancelButtonActionPerformed(evt);
            }
        });

        scaleConfirmButton.setText("OK");
        scaleConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleConfirmButtonActionPerformed(evt);
            }
        });

        scaleSlider.setMajorTickSpacing(50);
        scaleSlider.setMaximum(250);
        scaleSlider.setMinimum(50);
        scaleSlider.setMinorTickSpacing(10);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setValue(100);

        jLabel12.setText("Scale (%):");

        javax.swing.GroupLayout scaleDialogLayout = new javax.swing.GroupLayout(scaleDialog.getContentPane());
        scaleDialog.getContentPane().setLayout(scaleDialogLayout);
        scaleDialogLayout.setHorizontalGroup(
            scaleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scaleDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scaleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scaleDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(scaleConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scaleCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scaleSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(scaleDialogLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        scaleDialogLayout.setVerticalGroup(
            scaleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scaleDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(scaleSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(scaleDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scaleCancelButton)
                    .addComponent(scaleConfirmButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Changes have been made.");

        jLabel10.setText("Do you want to save before exiting?");

        exitDialogSaveButton.setText("Yes");
        exitDialogSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitDialogSaveButtonActionPerformed(evt);
            }
        });

        exitDialogExitButton.setText("No");
        exitDialogExitButton.setRequestFocusEnabled(false);
        exitDialogExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitDialogExitButtonActionPerformed(evt);
            }
        });

        exitDialogCancelButton.setText("Cancel");
        exitDialogCancelButton.setRequestFocusEnabled(false);
        exitDialogCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitDialogCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exitDialogLayout = new javax.swing.GroupLayout(exitDialog.getContentPane());
        exitDialog.getContentPane().setLayout(exitDialogLayout);
        exitDialogLayout.setHorizontalGroup(
            exitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitDialogLayout.createSequentialGroup()
                .addGroup(exitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(exitDialogLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exitDialogSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exitDialogExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exitDialogCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(exitDialogLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel10))
                    .addGroup(exitDialogLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exitDialogLayout.setVerticalGroup(
            exitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(exitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitDialogSaveButton)
                    .addComponent(exitDialogExitButton)
                    .addComponent(exitDialogCancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Image Editor");
        setMinimumSize(new java.awt.Dimension(700, 550));

        logList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(logList);

        scaleButton.setText("Scale...");
        scaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleButtonActionPerformed(evt);
            }
        });
        scaleButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                scaleButtonKeyPressed(evt);
            }
        });

        openButton.setText("Open...");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        openButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                openButtonKeyPressed(evt);
            }
        });

        saveButton.setText("Save...");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        saveButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saveButtonKeyPressed(evt);
            }
        });

        addTextButton.setText("Add text...");
        addTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTextButtonActionPerformed(evt);
            }
        });
        addTextButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addTextButtonKeyPressed(evt);
            }
        });

        settingsButton.setText("Settings...");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        settingsButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                settingsButtonKeyPressed(evt);
            }
        });

        imageComponent1.setOpaque(false);

        javax.swing.GroupLayout imageComponent1Layout = new javax.swing.GroupLayout(imageComponent1);
        imageComponent1.setLayout(imageComponent1Layout);
        imageComponent1Layout.setHorizontalGroup(
            imageComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        imageComponent1Layout.setVerticalGroup(
            imageComponent1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        openMenuItem.setText("Open...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save...");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);
        fileMenu.add(jSeparator1);

        settingsMenuItem.setText("Settings...");
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(settingsMenuItem);
        fileMenu.add(jSeparator2);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        undoMenuItem.setText("Undo");
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(undoMenuItem);

        redoMenuItem.setText("Redo");
        redoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(redoMenuItem);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(imageComponent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(openButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addTextButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsButton)
                        .addGap(0, 152, Short.MAX_VALUE))
                    .addComponent(imageComponent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        int returnVal = fc.showOpenDialog(HTInterface.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();            
            try {
                // remove text used from previously opened image
                Component[] textLabels = imageComponent1.getComponents();
                if (textLabels != null) {
                    for (int i = 0; i < textLabels.length; i++) {
                        if (textLabels[i] != null)
                            imageComponent1.remove(textLabels[i]);
                    }
                }
                
                // Read file, resize component, two new variables for undo and redo
                BufferedImage undoImage = origImage;
                origImage = ImageIO.read(file);
                imageComponent1.setImage(origImage);
                imageComponent1.setSize(origImage.getWidth(), origImage.getHeight());
                imageComponent1.repaint();
                
                fileSaved = false;
                
                checkListSize();
                listModel.insertElementAt("Opened a file", 0);
                BufferedImage redoImage = origImage;
                
                if (!undoing) {
                    undos.addEdit(new AbstractUndoableEdit() {
                        @Override
                        public void undo() {
                            // Sets the previous image as current image, adds the removed text back
                            super.undo();
                            undoing = true;
                            imageComponent1.setImage(undoImage);
                            if (undoImage != null)
                                imageComponent1.setSize(undoImage.getWidth(), undoImage.getHeight());
                            else
                                imageComponent1.setSize(0, 0);
                            if (textLabels != null) {
                                for (int i = 0; i < textLabels.length; i++) {
                                    if (textLabels[i] != null)
                                        imageComponent1.add(textLabels[i]);
                                }
                            }
                            imageComponent1.repaint();
                            listModel.removeElementAt(0);
                            undoing = false;
                        }
                        
                        @Override
                        public void redo() {
                            // Sets the new image as current one, removes the text
                            super.redo();
                            undoing = true;
                            imageComponent1.setImage(redoImage);
                            imageComponent1.setSize(redoImage.getWidth(), redoImage.getHeight());
                            if (textLabels != null) {
                                for (int i = 0; i < textLabels.length; i++) {
                                    if (textLabels[i] != null)
                                        imageComponent1.remove(textLabels[i]);
                                }
                            }
                            imageComponent1.repaint();
                            listModel.insertElementAt("Opened a file", 0);
                            undoing = false;
                        }
                    });
                }
            } catch (IOException ex) {
                Logger.getLogger(HTInterface.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_openButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (origImage != null) {
            int returnVal = fc.showSaveDialog(HTInterface.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                boolean approveSave = true;
                String filePath = fc.getSelectedFile().getAbsolutePath();
                
                // Forces .png save and checks if given file exists
                // Extremely long file path crashed the program
                if (filePath.length() > 256)
                    filePath = filePath.substring(0, 256);
                if (!filePath.endsWith(".png"))
                    filePath = filePath + ".png";
                File f = new File(filePath);
                int result = -1;
                if (f.isFile())
                    result = JOptionPane.showConfirmDialog(this, "This file exists. "
                            + "Do you want to overwrite it?", "Existing file", JOptionPane.OK_CANCEL_OPTION);
                
                // If the file exists, user doesn't want to overwrite it and user clicked "Save"
                // on file chooser, program goes for another round
                while(f.isFile() && result != JOptionPane.OK_OPTION && returnVal == JFileChooser.APPROVE_OPTION) {
                    returnVal = fc.showSaveDialog(HTInterface.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        filePath = fc.getSelectedFile().getAbsolutePath();
                        if (filePath.length() > 256)
                            filePath = filePath.substring(0, 256);
                        if (!filePath.endsWith(".png"))
                            filePath = filePath + ".png";
                        f = new File(filePath);
                    
                        if (f.isFile())
                            result = JOptionPane.showConfirmDialog(this, "This file exists. "
                                + "Do you want to overwrite it?", "Existing file", JOptionPane.OK_CANCEL_OPTION);
                        else
                            result = JOptionPane.OK_OPTION;
                    }
                    // If user wants to overwrite the existing file and clicked "Save"
                    // save has been approved
                    if (result == JOptionPane.OK_OPTION && returnVal == JFileChooser.APPROVE_OPTION)
                        approveSave = true;
                    else
                        approveSave = false;
                }

                if (approveSave) {
                    try {
                        // Makes a copy of the current image and paints all the parts
                        // into the copy.
                        BufferedImage bbb = new BufferedImage(origImage.getWidth(),
                            origImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g22 = bbb.createGraphics();
                        imageComponent1.paintComponent(g22);
                        imageComponent1.paintComponents(g22);
                        g22.dispose();
                        
                        // Writes to file and updates the log
                        File outputFile = new File(filePath);
                        ImageIO.write(bbb, "png", outputFile);

                        checkListSize();
                        listModel.insertElementAt("Saved a file", 0);
                        fileSaved = true;
                    } catch (IOException ex) {
                        Logger.getLogger(HTInterface.class.getName()).log(Level.SEVERE, null, ex);
                        fileSaved = false;
                    }
                }
            }
            else
                fileSaved = false;
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        settingsDialog.setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void addTextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTextButtonActionPerformed
        addTextDialog.setVisible(true);
    }//GEN-LAST:event_addTextButtonActionPerformed

    private void cancelAddTextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAddTextButtonActionPerformed
        addTextDialog.setVisible(false);
        addableText.setText("");
    }//GEN-LAST:event_cancelAddTextButtonActionPerformed

    private void addTextDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTextDialogButtonActionPerformed
        if (origImage != null) {
            // If there is an image opened, gets the text from dialog, cuts it down if it's
            // too long, creates JLabel for it with font created from settings
            fileSaved = false;
            String s = addableText.getText();
            if (s.length() > 50)
                s = s.substring(0, 50);
            BufferedImage bi = origImage;
            Graphics2D cg = bi.createGraphics();
            JLabel textLabel = new JLabel(s);
            Font f = new Font(origFontName, origFontStyle, origFontSize);
            textLabel.setFont(f);
            textLabel.setForeground(origFontColor);

            // Giving enough space to JLabel and placing it somewhat middle of the component
            FontMetrics metrics = cg.getFontMetrics(f);
            int hgt = metrics.getHeight();
            int adv = metrics.stringWidth(s);
            Dimension size = new Dimension(adv + 10, hgt);
            textLabel.setMaximumSize(size);
            textLabel.setSize(size);
            textLabel.setLocation((int)(imageComponent1.getWidth() / 2 - adv / 2),
                    (int)(imageComponent1.getHeight() / 2 - hgt / 2));
            
            // Mouselisteners for dragging the text, adding the label into picture
            textLabel.addMouseListener(myMouseAdapter);
            textLabel.addMouseMotionListener(myMouseAdapter);
            imageComponent1.add(textLabel);
            imageComponent1.repaint();

            checkListSize();
            listModel.insertElementAt("Added text", 0);
            if (!undoing) {
                undos.addEdit(new AbstractUndoableEdit() {
                    @Override
                    public void undo() {
                        // Remove the added text and log entry
                        super.undo();
                        undoing = true;
                        imageComponent1.remove(textLabel);
                        imageComponent1.validate();
                        imageComponent1.repaint();
                        listModel.removeElementAt(0);
                        undoing = false;
                    }

                    @Override
                    public void redo() {
                        // Add back the removed text and log entry
                        super.redo();
                        undoing = true;
                        imageComponent1.add(textLabel);
                        imageComponent1.repaint();
                        listModel.insertElementAt("Added text", 0);
                        undoing = false;
                    }
                });
            }
        }
        addTextDialog.setVisible(false);
        addableText.setText("");
    }//GEN-LAST:event_addTextDialogButtonActionPerformed

    private void settingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsCancelButtonActionPerformed
        // Set settings back to original values if user doesn't save the changes
        settingsFontName.setSelectedItem(origFontName);
        settingsFontStyle.setSelectedIndex(origFontStyle);
        settingsFontSize.setSelectedItem(Integer.toString(origFontSize));
        settingsRecentChanges.setValue(undos.getLimit());
        settingsDialog.setVisible(false);
    }//GEN-LAST:event_settingsCancelButtonActionPerformed

    private void settingsSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsSaveButtonActionPerformed
        // Set original values to currently chosen settings if user saves the changes
        origFontName = (String)settingsFontName.getSelectedItem();
        origFontStyle = settingsFontStyle.getSelectedIndex();
        origFontSize = Integer.parseInt((String)settingsFontSize.getSelectedItem());
        origChangesStored = (int)settingsRecentChanges.getValue();
        undos.setLimit((int)settingsRecentChanges.getValue());
        updateListSize();
        settingsDialog.setVisible(false);
    }//GEN-LAST:event_settingsSaveButtonActionPerformed

    private void settingsColorChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsColorChooserButtonActionPerformed
        // implementation of JColorChooser which returns the chosen color
        colorDialog = JColorChooser.createDialog(settingsDialog, "Color Chooser", true, cc,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        returnColor = cc.getColor();
                        colorDialog.setVisible(false);
                    }
                },
                null);
        colorDialog.setVisible(true);
        // If user chose a color, set the color as the color of the preview panel in settings
        // and change the currently chosen color
        if (returnColor != null) {
            origFontColor = returnColor;
            settingsFontColorPanel.setBackground(origFontColor);
        }
    }//GEN-LAST:event_settingsColorChooserButtonActionPerformed

    private void scaleCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleCancelButtonActionPerformed
        scaleDialog.setVisible(false);
        scaleSlider.setValue(100);
    }//GEN-LAST:event_scaleCancelButtonActionPerformed

    private void scaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleButtonActionPerformed
        scaleDialog.setVisible(true);
    }//GEN-LAST:event_scaleButtonActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        if (undos.canUndo())
            undos.undo();
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        if (undos.canRedo())
            undos.redo();       
    }//GEN-LAST:event_redoMenuItemActionPerformed

    private void scaleConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleConfirmButtonActionPerformed
        scaleDialog.setVisible(false);
        
        if (origImage != null) {
            // Get the scaling, and create a scaled version of the image
            fileSaved = false;
            scaleFactor = scaleSlider.getValue();
            double scale = scaleFactor;
            scale = scale / 100.0;
            int w = origImage.getWidth();
            int h = origImage.getHeight();
            BufferedImage scaledImage = new BufferedImage((int)(w * scale),
                    (int)(h * scale), TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(scale, scale);
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            scaledImage = scaleOp.filter(origImage, scaledImage);
            imageComponent1.setSize((int)(imageComponent1.getWidth() * scale),
                    (int)(imageComponent1.getHeight() * scale));
            imageComponent1.setImage(scaledImage);
            
            // Rescale text by recalculating the space it needs with scaled font size
            rescaleText(scaledImage, scale);
            imageComponent1.repaint();

            checkListSize();
            listModel.insertElementAt("Resized the image", 0);
            // UndoManager didn't accept these variables without creating a new variable
            // for them
            double scaleHere = scale;
            BufferedImage undoImage = origImage;
            // Setting the scaled version as the current image
            origImage = scaledImage;
            // State variable
            BufferedImage redoImage = scaledImage;
            if (!undoing) {
                undos.addEdit(new AbstractUndoableEdit() {
                    @Override
                    public void undo() {
                        // Set size and image to previous ones
                        super.undo();
                        undoing = true;
                        imageComponent1.setSize(undoImage.getWidth(), undoImage.getHeight());
                        imageComponent1.setImage(undoImage);
                        rescaleText(undoImage, 1 / scaleHere);
                        imageComponent1.repaint();
                        origImage = undoImage;
                        listModel.removeElementAt(0);
                        undoing = false;
                    }

                    @Override
                    public void redo() {
                        // Set size and image to new ones
                        super.redo();
                        undoing = true;
                        imageComponent1.setSize(redoImage.getWidth(), redoImage.getHeight());
                        imageComponent1.setImage(redoImage);
                        rescaleText(redoImage, scaleHere);
                        imageComponent1.repaint();
                        origImage = redoImage;
                        listModel.insertElementAt("Resized the image", 0);
                        undoing = false;
                    }
                });
            }
        }
        scaleSlider.setValue(100);
    }//GEN-LAST:event_scaleConfirmButtonActionPerformed

    private void openButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_openButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            openButtonActionPerformed(null);
    }//GEN-LAST:event_openButtonKeyPressed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        openButtonActionPerformed(null);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveButtonActionPerformed(null);
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void settingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsMenuItemActionPerformed
        settingsButtonActionPerformed(null);
    }//GEN-LAST:event_settingsMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // Check if file should be saved
        if (!fileSaved) {
            JComponent c = (JComponent)getContentPane();
            exitDialog.setLocationRelativeTo(c);
            exitDialog.setVisible(true);
        }
        else
            System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void exitDialogSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitDialogSaveButtonActionPerformed
        // User pressed save on confirmation dialog
        saveButtonActionPerformed(null);
        if (fileSaved) {
            exitDialog.setVisible(false);
            System.exit(0);
        }
        else
            exitDialog.setVisible(false);
    }//GEN-LAST:event_exitDialogSaveButtonActionPerformed

    private void exitDialogExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitDialogExitButtonActionPerformed
        exitDialog.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_exitDialogExitButtonActionPerformed

    private void exitDialogCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitDialogCancelButtonActionPerformed
        exitDialog.setVisible(false);
    }//GEN-LAST:event_exitDialogCancelButtonActionPerformed

    private void saveButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saveButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            saveButtonActionPerformed(null);
    }//GEN-LAST:event_saveButtonKeyPressed

    private void addTextButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addTextButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            addTextButtonActionPerformed(null);
    }//GEN-LAST:event_addTextButtonKeyPressed

    private void scaleButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scaleButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            scaleButtonActionPerformed(null);
    }//GEN-LAST:event_scaleButtonKeyPressed

    private void settingsButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_settingsButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            settingsButtonActionPerformed(null);
    }//GEN-LAST:event_settingsButtonKeyPressed
    
    // Removes the oldest edit from log list
    private void checkListSize() {
        if (listModel.getSize() >= undos.getLimit())
            listModel.removeElementAt(undos.getLimit() - 1);
    }
    
    // Removes multiple edits from log list if user changes settings
    private void updateListSize() {
        if (listModel.getSize() >= undos.getLimit())
            listModel.removeRange(undos.getLimit(), listModel.getSize() - 1);
    }
    
    // Filters files showed to images only
    private class ImageFilter extends FileFilter {
        public final String jpeg = "jpeg";
        public final String jpg = "jpg";
        public final String png = "png";
        public final String gif = "gif";
        
        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            
            String extension = getExtension(f);
            if (extension != null) {
                if (extension.equals(jpeg) ||
                    extension.equals(jpg) ||
                    extension.equals(png) ||
                    extension.equals(gif)) {
                    return true;
                }
                else
                    return false;
            }
            return false;
        }
        
        public String getDescription() {
            return "Images";
        }
        
        public String getExtension (File fi) {
            String ext = null;
            String s = fi.getName();
            int i = s.lastIndexOf('.');
            if (i > 0 && i < s.length() - 1)
                ext = s.substring(i + 1).toLowerCase();
            return ext;
        }
    }
    
    // Mouselistener for JLabels
    private class MyMouseAdapter extends MouseAdapter {
        private Point initLabelLocation = null;
        private Point initMouseLocation = null;
        private JLabel label = null; //
        private int labelX; //
        private int labelY; //
        private boolean dragged = false;
        
        @Override
        public void mousePressed(MouseEvent e) {
            label = (JLabel)e.getSource();
            initLabelLocation = label.getLocation();
            initMouseLocation = e.getLocationOnScreen();
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (dragged && !undoing) {
                dragged = false;
                checkListSize();
                listModel.insertElementAt("Moved text", 0);
                Point undoLabelLocation = initLabelLocation;
                Point redoLabelLocation = new Point(labelX, labelY);
                JLabel undoLabel = label;
                //Point newLabelLocation = new Point(labelX, labelY);
                // Undo sets the location to starting point
                // Redo sets the location to the new point
                undos.addEdit(new AbstractUndoableEdit () {
                    @Override
                    public void undo() {
                        super.undo();
                        undoing = true;
                        undoLabel.setLocation(undoLabelLocation);
                        listModel.removeElementAt(0);
                        undoing = false;
                    }
                    
                    @Override
                    public void redo() {
                        super.redo();
                        undoing = true;
                        undoLabel.setLocation(redoLabelLocation);
                        listModel.insertElementAt("Moved text", 0);
                        undoing = false;
                    }
                });
                
            }
            initLabelLocation = null;
            initMouseLocation = null;
            labelX = 0;
            labelY = 0;
        }
        
        // Provides visual feedback when user drags the label
        @Override
        public void mouseDragged(MouseEvent e) {
            if (initLabelLocation != null && initMouseLocation != null) {                
                dragged = true;
                label = (JLabel)e.getSource();
                Point mouseLocation = e.getLocationOnScreen();
                
                int deltaX = mouseLocation.x - initMouseLocation.x;
                int deltaY = mouseLocation.y - initMouseLocation.y;
                
                labelX = initLabelLocation.x + deltaX;
                labelY = initLabelLocation.y + deltaY;
                
                label.setLocation(labelX, labelY);
            }
        }
    }
    
    // Rescales the text by making the font size bigger
    // and by recalculating the space it needs
    private void rescaleText(BufferedImage bui, double scaleDouble) {
        Component[] labels = imageComponent1.getComponents();
        BufferedImage bbb = bui;
        Graphics2D g2g2 = bbb.createGraphics();
        for (int i = 0; i < labels.length; i++) {
            Component c = labels[i];
            if (c != null) {
                JLabel curr = (JLabel)c;
                
                Font f = curr.getFont();
                int fontSize = f.getSize();
                Font fo = f.deriveFont((float)(fontSize * scaleDouble));
                curr.setFont(fo);

                FontMetrics metrics = g2g2.getFontMetrics(fo);
                int hgt = metrics.getHeight();
                int adv = metrics.stringWidth(curr.getText());
                Dimension size = new Dimension(adv + 10, hgt);
                curr.setMaximumSize(size);
                curr.setSize(size);
                Point labelLoc = curr.getLocation();
                curr.setBounds((int)(labelLoc.x * scaleDouble), (int)(labelLoc.y * scaleDouble),
                        curr.getWidth(), curr.getHeight());
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HTInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HTInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HTInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HTInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HTInterface().setVisible(true);
            }
        });
    }
    
    // File choosers, color dialog, currently shown image
    final JFileChooser fc = new JFileChooser();
    JColorChooser cc = new JColorChooser();
    JDialog colorDialog;
    BufferedImage origImage;
    MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
    
    // Holds the values for currently selected settings
    String origFontName;
    Color origFontColor;
    Color returnColor;
    int origFontStyle;
    int origChangesStored;
    int origFontSize;
    int scaleFactor;
    boolean fileSaved = true;
    
    // Log list
    DefaultListModel listModel = new DefaultListModel();
    
    // Undo manager
    UndoManager undos = new UndoManager();
    boolean undoing = false;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTextButton;
    private javax.swing.JDialog addTextDialog;
    private javax.swing.JButton addTextDialogButton;
    private javax.swing.JTextField addableText;
    private javax.swing.JButton cancelAddTextButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JDialog exitDialog;
    private javax.swing.JButton exitDialogCancelButton;
    private javax.swing.JButton exitDialogExitButton;
    private javax.swing.JButton exitDialogSaveButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private ImageComponent imageComponent1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JList<String> logList;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton openButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton scaleButton;
    private javax.swing.JButton scaleCancelButton;
    private javax.swing.JButton scaleConfirmButton;
    private javax.swing.JDialog scaleDialog;
    private javax.swing.JSlider scaleSlider;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton settingsCancelButton;
    private javax.swing.JButton settingsColorChooserButton;
    private javax.swing.JDialog settingsDialog;
    private javax.swing.JPanel settingsFontColorPanel;
    private javax.swing.JComboBox<String> settingsFontName;
    private javax.swing.JComboBox<String> settingsFontSize;
    private javax.swing.JComboBox<String> settingsFontStyle;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JSpinner settingsRecentChanges;
    private javax.swing.JButton settingsSaveButton;
    private javax.swing.JMenuItem undoMenuItem;
    // End of variables declaration//GEN-END:variables
}
