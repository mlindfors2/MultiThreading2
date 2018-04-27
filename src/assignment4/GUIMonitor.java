
package assignment4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * The GUI for assignment 4
 */
public class GUIMonitor implements ActionListener {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code
	 */
	private JFrame frame; // The Main window
	private JMenu fileMenu; // The menu
	private JMenuItem openItem; // File - open
	private JMenuItem saveItem; // File - save as
	private JMenuItem exitItem; // File - exit
	private JTextField txtFind; // Input string to find
	private JTextField txtReplace; // Input string to replace
	private JCheckBox chkNotify; // User notification choise
	private JLabel lblInfo; // Hidden after file selected
	private JButton btnCreate; // Start copying
	private JButton btnClear; // Removes dest. file and removes marks
	private JLabel lblChanges; // Label telling number of replacements
	private JFileChooser jFC;
	private JTextPane jTextPaneSource;
	private JTextPane jTextPaneDest;
	private BoundedBuffer buffer;
	private Writer writer;
	private Reader reader;
	private Modifier modifier;

	/**
	 * Constructor
	 */
	public GUIMonitor() {
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 714, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Text File Copier - with Find and Replace");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open Source File");
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		saveItem = new JMenuItem("Save Destination File As");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.setEnabled(false);
		exitItem = new JMenuItem("Exit");
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		bar.add(fileMenu);

		JPanel pnlFind = new JPanel();
		pnlFind.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Find and Replace"));
		pnlFind.setBounds(12, 32, 436, 122);
		pnlFind.setLayout(null);
		frame.add(pnlFind);
		JLabel lab1 = new JLabel("Find:");
		lab1.setBounds(7, 30, 80, 13);
		pnlFind.add(lab1);
		JLabel lab2 = new JLabel("Replace with:");
		lab2.setBounds(7, 63, 80, 13);
		pnlFind.add(lab2);

		txtFind = new JTextField();
		txtFind.setBounds(88, 23, 327, 20);
		pnlFind.add(txtFind);
		txtReplace = new JTextField();
		txtReplace.setBounds(88, 60, 327, 20);
		pnlFind.add(txtReplace);
		chkNotify = new JCheckBox("Notify user on every match");
		chkNotify.setBounds(88, 87, 180, 17);
		pnlFind.add(chkNotify);

		lblInfo = new JLabel("Select Source File..");
		lblInfo.setBounds(485, 42, 120, 13);
		frame.add(lblInfo);

		btnCreate = new JButton("Create the destination file");
		btnCreate.setBounds(465, 119, 230, 23);
		frame.add(btnCreate);
		btnClear = new JButton("Clear Dest. file and remove marks");
		btnClear.setBounds(465, 151, 230, 23);
		frame.add(btnClear);

		lblChanges = new JLabel("No. of Replacements:");
		lblChanges.setBounds(279, 161, 200, 13);
		frame.add(lblChanges);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 170, 653, 359);
		frame.add(tabbedPane);
		jTextPaneSource = new JTextPane();
		jTextPaneDest = new JTextPane();
		JScrollPane scrollSource = new JScrollPane(jTextPaneSource);
		tabbedPane.addTab("Source", null, scrollSource, null);
		JScrollPane scrollDest = new JScrollPane(jTextPaneDest);
		tabbedPane.addTab("Destination", null, scrollDest, null);
		openItem.addActionListener(this);
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(this);
	}

	/**
	 * Method that reads a file from harddrive and stores every line in a LinkedList
	 * as string.
	 * 
	 * @return LinkedList<String> with a series of String lines.
	 */
	private LinkedList<String> readFile() {
		String readLine;
		LinkedList<String> list = new LinkedList<String>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(jFC.getSelectedFile()), "ISO-8859-1"))) {
			readLine = br.readLine();
			while (readLine != null) {
				list.add(readLine);
				readLine = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ButtonListener that listen on the Filemenu and handles the 'Open source file'
	 * alternative. If its chosen a file chooser will open to let the user select a
	 * file from the harddrive to open and it will display to content in the source
	 * JTextPane.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openItem) {
			jFC = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jFC.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				LinkedList<String> list = readFile();
				for (int i = 0; i < list.size(); i++) {
					jTextPaneSource.setText(jTextPaneSource.getText() + "\n" + list.get(i));
				}
			}
			btnCreate.setEnabled(true);
		}
		/**
		 * If btnCreate button is clicked a buffer and 3 threads with a writer, modifier
		 * and a reader will be started. Number of words + newLines will be counted and used
		 * for input to reader and modifier.
		 */
		if (e.getSource() == btnCreate) {
			buffer = new BoundedBuffer(15, txtFind.getText() + " ", txtReplace.getText() + " ");
			writer = new Writer(buffer, jTextPaneSource);
			writer.startThread();
			int nbrOfWords = writer.getNumberOfWords();
			int nbrBlankLinkes = writer.fetchLinesFromTextPane().length;
			nbrOfWords = nbrOfWords + nbrBlankLinkes;
			modifier = new Modifier(buffer, nbrOfWords);
			modifier.startThread();
			reader = new Reader(buffer, jTextPaneDest, nbrOfWords);
			reader.startThread();
		}
	}
}
