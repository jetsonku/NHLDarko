import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.*;

public class HelloWorld {
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;

	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField txtSearch1;

	private static int currentCalories;
	private static int waterCount;

	public static String[] foodAndDrinks = new String[] {"Banana", "Apple", "Orange", "Egg", "Pancake", "Kiwi", "Bacon"};

	private static JTextField textField_7;	
	private static JTextField textField_8;	
	private static JTextField txtOfYourGoal;	
	private static JTextField txtWaterConsumed;	
	private static JTextField textField_9;	
	private static JTextField textField_10;	
	private static JTextField textField_11;	
	private static JTextField txtSettings;	
	private static JTextField txtSearch;	
    private static JTable table;	
    private static final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		


		final HashMap<String, Integer> foodMap = new HashMap<String, Integer>(); 
		foodMap.put("Banana", 105);
		foodMap.put("Apple", 95);
		foodMap.put("Orange", 45);
		foodMap.put("Egg", 78);
		foodMap.put("Pancake", 64);
		foodMap.put("Kiwi", 42);
		foodMap.put("Bacon", 43);

		// Setting up tabs 
		JFrame f = new JFrame("Health App Prototype");
		f.setSize(432, 768);

		JTabbedPane pane = new JTabbedPane();
		pane.setBackground(Color.decode("#f7f7e1"));
		pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pane.setTabPlacement(JTabbedPane.BOTTOM);
		final JPanel panel1 = new JPanel(); 
		panel1.setBackground(Color.decode("#f7f7e1"));
		JPanel panel2 = new JPanel(); 
		panel2.setBackground(Color.decode("#f7f7e1"));
		JPanel panel4 = new JPanel(); 
		panel4.setBackground(Color.decode("#f7f7e1"));
		JPanel panel5 = new JPanel();
		panel5.setBackground(Color.decode("#f7f7e1"));


        // Panel1 Workout Tab 
        pane.addTab("", panel1);
        panel1.setLayout(null);
        
		final JTextArea txtrWorkout = new JTextArea();
		txtrWorkout.setBounds(30, 189, 105, 142);
		txtrWorkout.setEditable(false);
		txtrWorkout.setText("Name \n");
		txtrWorkout.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel1.add(txtrWorkout);
		
		final JTextArea txtrIntensity = new JTextArea();
		txtrIntensity.setBounds(144, 189, 75, 142);
		txtrIntensity.setEditable(false);
		txtrIntensity.setText("Intensity \n");
		txtrIntensity.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel1.add(txtrIntensity);

		final JTextArea txtrDuration = new JTextArea();
		txtrDuration.setBounds(228, 189, 75, 142);
		txtrDuration.setEditable(false);
		txtrDuration.setText("Duration \n");
		txtrDuration.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel1.add(txtrDuration);

		final JTextArea txtrBurned = new JTextArea();
		txtrBurned.setBounds(312, 189, 75, 142);
		txtrBurned.setEditable(false);
		txtrBurned.setText("Calories \n");
		txtrBurned.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel1.add(txtrBurned);

        final JInternalFrame logWorkoutWindow = new JInternalFrame("Log Workout", true, true, true, true);
        final JInternalFrame workoutHistory = new JInternalFrame("Workout History", true, true, true, true);

        ButtonGroup bg = new ButtonGroup();
		logWorkoutWindow.getContentPane().setBackground(new Color(247, 247, 225));
		logWorkoutWindow.setBounds(30, 28, 351, 600);
		logWorkoutWindow.getContentPane().setLayout(null);
		
		JLabel exercise_label = new JLabel("Add Exercise");
		exercise_label.setFont(new Font("Tahoma", Font.PLAIN, 21));
		exercise_label.setBounds(20, 10, 203, 42);
		logWorkoutWindow.getContentPane().add(exercise_label);
		
        String workout_types[] = { "Run", "Walk", "Weights", "Other"};
		final JComboBox workout_dropdown = new JComboBox(workout_types);
		workout_dropdown.setSelectedIndex(-1);
		workout_dropdown.setBounds(20, 98, 148, 22);
		logWorkoutWindow.getContentPane().add(workout_dropdown);
		
		JLabel workouttype_label = new JLabel("Workout Type");
		workouttype_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		workouttype_label.setBounds(20, 65, 121, 24);
		logWorkoutWindow.getContentPane().add(workouttype_label);
		
		
		JLabel duration_label = new JLabel("Duration");
		duration_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		duration_label.setBounds(20, 215, 121, 24);
		logWorkoutWindow.getContentPane().add(duration_label);
		
		final JLabel hours_label = new JLabel("Hours");
		hours_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		hours_label.setBounds(90, 251, 46, 14);
		logWorkoutWindow.getContentPane().add(hours_label);
		
		final JLabel minutes_label = new JLabel("Minutes");
		minutes_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minutes_label.setBounds(208, 251, 46, 14);
		logWorkoutWindow.getContentPane().add(minutes_label);
		
        String intensity_types[] = {"Intense", "Medium", "Light"};
		final JComboBox intensity_dropdown = new JComboBox(intensity_types);
		intensity_dropdown.setSelectedIndex(-1);
		intensity_dropdown.setBounds(20, 325, 148, 22);
		logWorkoutWindow.getContentPane().add(intensity_dropdown);
		
		JLabel intensity_label = new JLabel("Intensity");
		intensity_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		intensity_label.setBounds(20, 290, 121, 24);
		logWorkoutWindow.getContentPane().add(intensity_label);
		

		
		final JButton log_button = new JButton("Log");

		log_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		log_button.setBounds(78, 508, 179, 35);
		logWorkoutWindow.getContentPane().add(log_button);
		
		JLabel colon_label = new JLabel(":");
		colon_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		colon_label.setBounds(86, 172, 17, 24);
		logWorkoutWindow.getContentPane().add(colon_label);
		
		JRadioButtonMenuItem AM_button = new JRadioButtonMenuItem("AM");
		AM_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(AM_button);
		AM_button.setBounds(155, 172, 54, 26);
		logWorkoutWindow.getContentPane().add(AM_button);
		
		JRadioButtonMenuItem PM_button = new JRadioButtonMenuItem("PM");
		PM_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(PM_button);
		PM_button.setBounds(212, 172, 81, 26);
		logWorkoutWindow.getContentPane().add(PM_button);
		
		JLabel time_label = new JLabel("Time");
		time_label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		time_label.setBounds(20, 140, 121, 24);
		logWorkoutWindow.getContentPane().add(time_label);
		
		SpinnerModel hour_values = new SpinnerNumberModel(1, 1, 12, 1);
		JSpinner start_spinner = new JSpinner(hour_values);
		start_spinner.setFont(new Font("Tahoma", Font.BOLD, 12));
		start_spinner.setBounds(20, 175, 62, 20);
		logWorkoutWindow.getContentPane().add(start_spinner);
		
		SpinnerModel startmin_values = new SpinnerNumberModel(00, 00, 59, 1);
		JSpinner startm_spinner = new JSpinner(startmin_values);
		startm_spinner.setEditor(new JSpinner.NumberEditor(startm_spinner, "00"));
		startm_spinner.setFont(new Font("Tahoma", Font.BOLD, 12));
		startm_spinner.setBounds(96, 175, 54, 20);
		logWorkoutWindow.getContentPane().add(startm_spinner);
		
		SpinnerModel dur_values = new SpinnerNumberModel(00, 00, 59, 1);
		final JSpinner duration_spinner = new JSpinner((dur_values));
		duration_spinner.setFont(new Font("Tahoma", Font.BOLD, 12));
		duration_spinner.setBounds(20, 248, 62, 20);
		logWorkoutWindow.getContentPane().add(duration_spinner);
		
		SpinnerModel durmin_values = new SpinnerNumberModel(00, 00, 59, 1);
		final JSpinner durationm_spinner = new JSpinner((durmin_values));
		durationm_spinner.setEditor(new JSpinner.NumberEditor(durationm_spinner, "00"));
		durationm_spinner.setFont(new Font("Tahoma", Font.BOLD, 12));
		durationm_spinner.setBounds(138, 248, 62, 20);
		logWorkoutWindow.getContentPane().add(durationm_spinner);
		

		


		panel1.add(logWorkoutWindow);
        final JButton btnLogWorkout = new JButton("Log Workout");
        btnLogWorkout.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	logWorkoutWindow.setVisible(true);
            	logWorkoutWindow.toFront();
                panel1.setBackground(Color.decode("#93937d"));
                btnLogWorkout.setVisible(false);
                txtrWorkout.setVisible(false);
                txtrIntensity.setVisible(false);
                txtrDuration.setVisible(false);                
                txtrBurned.setVisible(false);                
            }
        });
        
        
		log_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                panel1.setBackground(Color.decode("#f7f7e1"));

                btnLogWorkout.setVisible(true);
                txtrWorkout.setVisible(true);
                txtrIntensity.setVisible(true);
                txtrDuration.setVisible(true);                
                txtrBurned.setVisible(true);   
            	logWorkoutWindow.toBack();
            	logWorkoutWindow.setVisible(false);
            	String loggedName = (String) workout_dropdown.getSelectedItem();
            	txtrWorkout.setText(txtrWorkout.getText() + loggedName + '\n');
            	String loggedDuration = duration_spinner.getValue().toString() + ":" + String.format("%02d", durationm_spinner.getValue());
            	txtrDuration.setText(txtrDuration.getText() + loggedDuration + '\n');
            	String loggedIntensity = (String) intensity_dropdown.getSelectedItem();
            	txtrIntensity.setText(txtrIntensity.getText() + loggedIntensity + '\n');

            	double workoutmultiplier = 0; 
            	switch (loggedName) {
            	case "Run": workoutmultiplier = 1.5;
            		break;
            	case "Walk": workoutmultiplier = 0.5;
            		break;
            	case "Weights": workoutmultiplier = 1;
            		break;
            	}
            	double intensitymultiplier = 0; 
                switch (loggedIntensity) {
                case "Intense": intensitymultiplier = 1.5;
                	break;
                case "Medium": intensitymultiplier = 1.3;
                	break;
                case "Light": intensitymultiplier = 1;
                	break;            	
                	}
                
                int loggedCalories = ((Integer) duration_spinner.getValue() *60) + (Integer) durationm_spinner.getValue();
            	loggedCalories *= (2*intensitymultiplier*workoutmultiplier);
            	txtrBurned.setText(txtrBurned.getText() + loggedCalories + '\n');
            	currentCalories -= loggedCalories;
        		workout_dropdown.setSelectedIndex(-1);
        		intensity_dropdown.setSelectedIndex(-1);
			}
		});
		logWorkoutWindow.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                panel1.setBackground(Color.decode("#f7f7e1"));
                txtrWorkout.setVisible(true);
                txtrIntensity.setVisible(true);
                txtrDuration.setVisible(true);                
                txtrBurned.setVisible(true);
                btnLogWorkout.setVisible(true);

            }
        });
        

        btnLogWorkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnLogWorkout.setOpaque(true);
        btnLogWorkout.setForeground(Color.WHITE);
        btnLogWorkout.setFont(new Font("Dialog", Font.PLAIN, 21));
        btnLogWorkout.setBackground(new Color(89, 83, 112));
        btnLogWorkout.setBounds(77, 361, 256, 107);
        panel1.add(btnLogWorkout);

        JButton btnWorkoutHistory = new JButton("Workout History");
        btnWorkoutHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnWorkoutHistory.setOpaque(true);
        btnWorkoutHistory.setForeground(Color.WHITE);
        btnWorkoutHistory.setFont(new Font("Dialog", Font.PLAIN, 21));
        btnWorkoutHistory.setBackground(new Color(89, 83, 112));
        btnWorkoutHistory.setBounds(77, 497, 256, 107);
        panel1.add(btnWorkoutHistory);

        JLabel congrats_label = new JLabel("<html><div style='text-align: center;'>You reached your daily step goal! Well done!</div></html>");
        congrats_label.setFont(new Font("Dialog", Font.PLAIN, 19));

        congrats_label.setHorizontalAlignment(SwingConstants.CENTER);
        congrats_label.setBounds(25, 98, 361, 79);
        panel1.add(congrats_label);

        JProgressBar step_bar = new JProgressBar();
        step_bar.setForeground(new Color(0, 120, 215));
        step_bar.setValue(32);
        step_bar.setStringPainted(true);
        step_bar.setFont(new Font("Dialog", Font.PLAIN, 16));
        step_bar.setBounds(62, 64, 287, 24);
        panel1.add(step_bar);
        
        

        pane.setBackgroundAt(0, Color.WHITE);
        pane.setIconAt(0, new ImageIcon(HelloWorld.class.getResource("/Images/wout_icon.PNG")));

		pane.addTab("" , panel2);
		panel2.setLayout(null);
		pane.setBackgroundAt(1, Color.WHITE);
		pane.setIconAt(1, new ImageIcon(HelloWorld.class.getResource("/Images/food_icon.PNG")));

		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.decode("#f7f7e1"));
		pane.addTab("", panel3);
		pane.setBackgroundAt(2, Color.WHITE);
		pane.setEnabledAt(2, true);
		pane.setIconAt(2, new ImageIcon(HelloWorld.class.getResource("/Images/home_icon.PNG")));
		panel3.setLayout(null);

		JTextPane txtpnSteps_1 = new JTextPane();	
        txtpnSteps_1.setEditable(false);	
        txtpnSteps_1.setForeground(Color.decode("#52170b"));	
        txtpnSteps_1.setBackground(Color.decode("#f7f7e1"));	
        txtpnSteps_1.setBounds(39, 21, 104, 69);	
        txtpnSteps_1.setText("Steps\r\n");	
        txtpnSteps_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 35));	
        panel3.add(txtpnSteps_1);

        JSeparator separator = new JSeparator();	
        separator.setForeground(Color.BLACK);	
        separator.setPreferredSize(new Dimension(100, 100));	
        separator.setBounds(0, 101, 413, 7);	
        panel3.add(separator);

        JTextPane txtpnSteps_1_1 = new JTextPane();	
        txtpnSteps_1_1.setEditable(false);	
        txtpnSteps_1_1.setText("  Total  \r\nCalories");	
        txtpnSteps_1_1.setForeground(new Color(82, 23, 11));	
        txtpnSteps_1_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 35));	
        txtpnSteps_1_1.setBackground(new Color(247, 247, 225));	
        txtpnSteps_1_1.setBounds(22, 127, 151, 100);	
        panel3.add(txtpnSteps_1_1);

        JTextPane txtpnSteps_1_1_1 = new JTextPane();	
        txtpnSteps_1_1_1.setEditable(false);	
        txtpnSteps_1_1_1.setText(":");	
        txtpnSteps_1_1_1.setForeground(new Color(82, 23, 11));	
        txtpnSteps_1_1_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 35));	
        txtpnSteps_1_1_1.setBackground(new Color(247, 247, 225));	
        txtpnSteps_1_1_1.setBounds(174, 149, 17, 53);	
        panel3.add(txtpnSteps_1_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(100, 100));
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(0, 248, 413, 7);
		panel3.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setPreferredSize(new Dimension(100, 100));
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(229, 54, 125, 7);
		panel3.add(separator_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setPreferredSize(new Dimension(100, 100));
		separator_2_1.setForeground(Color.BLACK);
		separator_2_1.setBounds(229, 181, 125, 7);
		panel3.add(separator_2_1);

		JTextPane txtpnSteps_1_2 = new JTextPane();	
        txtpnSteps_1_2.setEditable(false);	
        txtpnSteps_1_2.setText("  Water \r\nProgress");	
        txtpnSteps_1_2.setForeground(new Color(82, 23, 11));	
        txtpnSteps_1_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 35));	
        txtpnSteps_1_2.setBackground(new Color(247, 247, 225));	
        txtpnSteps_1_2.setBounds(10, 265, 163, 114);	
        panel3.add(txtpnSteps_1_2);

        JTextPane txtpnSteps_1_1_1_1 = new JTextPane();	
        txtpnSteps_1_1_1_1.setEditable(false);	
        txtpnSteps_1_1_1_1.setText(":");	
        txtpnSteps_1_1_1_1.setForeground(new Color(82, 23, 11));	
        txtpnSteps_1_1_1_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 35));	
        txtpnSteps_1_1_1_1.setBackground(new Color(247, 247, 225));	
        txtpnSteps_1_1_1_1.setBounds(174, 284, 17, 53);	
        panel3.add(txtpnSteps_1_1_1_1);

        JTextPane txtpnSteps_1_1_1_2 = new JTextPane();	
        txtpnSteps_1_1_1_2.setEditable(false);	
        txtpnSteps_1_1_1_2.setText(":");	
        txtpnSteps_1_1_1_2.setForeground(new Color(82, 23, 11));	
        txtpnSteps_1_1_1_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 35));	
        txtpnSteps_1_1_1_2.setBackground(new Color(247, 247, 225));	
        txtpnSteps_1_1_1_2.setBounds(174, 21, 17, 53);	
        panel3.add(txtpnSteps_1_1_1_2);

        textField = new JTextField();	
        textField.setBackground(Color.decode("#f7f7e1"));	
        textField.setEditable(false);	
        textField.setBorder(null);	
        textField.setText("3207");	
        textField.setHorizontalAlignment(SwingConstants.CENTER);	
        textField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 35));	
        textField.setBounds(229, 10, 125, 34);	
        panel3.add(textField);	
        textField.setColumns(10);

        textField_1 = new JTextField();	
        textField_1.setText("6000");	
        textField_1.setEditable(false);	
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);	
        textField_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 35));	
        textField_1.setColumns(10);	
        textField_1.setBorder(null);	
        textField_1.setBackground(new Color(247, 247, 225));	
        textField_1.setBounds(229, 59, 125, 40);	
        panel3.add(textField_1);

        textField_2 = new JTextField();	
        textField_2.setText("2000");	
        textField_2.setEditable(false);	
        textField_2.setHorizontalAlignment(SwingConstants.CENTER);	
        textField_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 35));	
        textField_2.setColumns(10);	
        textField_2.setBorder(null);	
        textField_2.setBackground(new Color(247, 247, 225));	
        textField_2.setBounds(229, 182, 125, 45);	
        panel3.add(textField_2);

        textField_3 = new JTextField();	
        textField_3.setText("1265");	
        textField_3.setEditable(false);	
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);	
        textField_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 35));	
        textField_3.setColumns(10);	
        textField_3.setBorder(null);	
        textField_3.setBackground(new Color(247, 247, 225));	
        textField_3.setBounds(229, 137, 125, 34);	
        panel3.add(textField_3);

		String[] hourStrings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		String[] minuteStrings = new String[60];
		int j = 0;
		for(int i = 0; i < 60; i++) {
			if(String.valueOf(i).length() == 1) {
				minuteStrings[j] = "0" + String.valueOf(i);
			}
			else {
				minuteStrings[j] = String.valueOf(i);
			}

			j++;
		}

		final ButtonGroup inputTime = new ButtonGroup();

		final ArrayList<JRadioButton> inputTimeArray = new ArrayList<JRadioButton>(); 

		String[] amountStrings = { "whole", "g", "mg", "l", "ml", "oz", "fl oz", "c", "pt", "qt", "gal"  };




		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.BLUE);
		progressBar.setValue(73);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 35));
		progressBar.setBounds(216, 294, 125, 45);
		panel3.add(progressBar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HelloWorld.class.getResource("/Images/watb.png")));
		lblNewLabel.setBounds(211, 272, 202, 92);
		panel3.add(lblNewLabel);

		final DefaultListModel<String> foodListModel = new DefaultListModel<String>();


		final DefaultListModel<String> caloriesListModel = new DefaultListModel<String>();



		final DefaultListModel<String> timeListModel = new DefaultListModel<String>();


		final JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setClosable(true);
		//internalFrame.setClosable(true);
		internalFrame.setBounds(15, 24, 380, 646);
		panel2.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Item ");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblNewLabel_6.setBounds(6, 6, 77, 28);
		internalFrame.getContentPane().add(lblNewLabel_6);

		txtSearch1 = new JTextField();

		txtSearch1.setBounds(6, 34, 275, 26);
		internalFrame.getContentPane().add(txtSearch1);
		txtSearch1.setColumns(10);


		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.setBounds(273, 34, 77, 29);
		internalFrame.getContentPane().add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 57, 275, 176);
		internalFrame.getContentPane().add(scrollPane);
	

		final JList<String> list_1 = new JList<String>();

		scrollPane.setViewportView(list_1);
		list_1.setModel(new AbstractListModel<String>() {
			String[] values = foodAndDrinks;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		//JScrollPane scrollPane_1 = new JScrollPane();
		//scrollPane_1.setBounds(-5, 189, 334, 354);
		//internalFrame.getContentPane().add(scrollPane_1);
		
		final JList txtrCalories = new JList();
		txtrCalories.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtrCalories.setBounds(260, 189, 89, 142);
		txtrCalories.setModel(caloriesListModel);
		txtrCalories.setEnabled(false);
		panel2.add(txtrCalories);

		final JList txtrFood = new JList();
		txtrFood.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtrFood.setBounds(161, 189, 89, 142);
		txtrFood.setModel(timeListModel);
		txtrFood.setEnabled(false);
		panel2.add(txtrFood);

		final JList foodLogs = new JList();
		foodLogs.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		foodLogs.setBounds(60, 189, 89, 142);
		foodLogs.setModel(foodListModel);
	
		panel2.add(foodLogs);

//		scrollPane_1.add(foodLogs);
//		scrollPane_1.add(txtrCalories);
//		scrollPane_1.add(txtrFood);
//		panel2.add(scrollPane_1);
		
		

		list_1.setLayoutOrientation(JList.VERTICAL);
		
		final JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(0, 475, 356, 122);
		internalFrame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setIcon(new ImageIcon(HelloWorld.class.getResource("/Images/NewKeyboard.png")));
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setEnabled(false);
		
		final JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(305, 565, 51, 29);
		internalFrame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.setBackground(Color.decode("#f7f7e1"));		
		
		btnNewButton_3.setEnabled(true);
		
		btnNewButton_3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			
				lblNewLabel_5.setVisible(false);
				lblNewLabel_5.setEnabled(false);
				btnNewButton_3.setEnabled(false);
				
			}
		});
		

		txtSearch1.getDocument().addDocumentListener(new DocumentListener(){
			@Override public void insertUpdate(DocumentEvent e) { filterFoodAndDrinks(); }
			@Override public void removeUpdate(DocumentEvent e) { filterFoodAndDrinks(); }
			@Override public void changedUpdate(DocumentEvent e) {}
			private void filterFoodAndDrinks() {
				
				lblNewLabel_5.setVisible(true);
				lblNewLabel_5.setEnabled(true);
	
				btnNewButton_3.setEnabled(true);
				
				
				
				String filter = txtSearch1.getText();
				DefaultListModel<String> listModel = new DefaultListModel<>();
				for (int i = 0 ; i < foodAndDrinks.length; i++) {
					listModel.add(i, foodAndDrinks[i]);
				}

				list_1.setModel(listModel);

				for (int i = 0; i < foodAndDrinks.length; i++) {
					if (foodAndDrinks[i].startsWith(filter)) {
						if (!listModel.contains(foodAndDrinks[i])) {
							listModel.addElement(foodAndDrinks[i]);
						}

					} else {
						if (listModel.contains(foodAndDrinks[i])) {
							listModel.removeElement(foodAndDrinks[i]);
						}
					}
				}

			}
		});


		final JComboBox<String> comboBox_3 = new JComboBox(hourStrings);
		comboBox_3.setBounds(6, 274, 77, 27);
		internalFrame.getContentPane().add(comboBox_3);

		JLabel lblNewLabel_6_1 = new JLabel("Time");
		lblNewLabel_6_1.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblNewLabel_6_1.setBounds(6, 245, 77, 28);
		internalFrame.getContentPane().add(lblNewLabel_6_1);

		final JComboBox comboBox_3_1 = new JComboBox(minuteStrings);
		comboBox_3_1.setBounds(95, 274, 77, 27);
		internalFrame.getContentPane().add(comboBox_3_1);

		final JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("am");
		rdbtnNewRadioButton_2.setBounds(184, 274, 69, 23);
		internalFrame.getContentPane().add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_2_1 = new JRadioButton("pm");
		rdbtnNewRadioButton_2_1.setBounds(260, 274, 69, 23);
		internalFrame.getContentPane().add(rdbtnNewRadioButton_2_1);
		inputTime.add(rdbtnNewRadioButton_2);
		inputTime.add(rdbtnNewRadioButton_2_1);
		inputTimeArray.add(rdbtnNewRadioButton_2);
		inputTimeArray.add(rdbtnNewRadioButton_2_1);
		
		final JButton btnNewButton2 = new JButton("Log Food or Drink");
		final JButton btnSubmit = new JButton("Submit");

		btnNewButton2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				internalFrame.setVisible(true);
				internalFrame.setEnabled(true);
				btnSubmit.setEnabled(true);
				internalFrame.toFront();
				btnNewButton2.setVisible(false);
				foodLogs.setVisible(false);
				txtrCalories.setVisible(false);
				txtrFood.setVisible(false);
				

				// after you submit set everything to empty and then if its empty dont add to list

			}
		});

		JLabel lblNewLabel_6_1_1 = new JLabel("Amount");
		lblNewLabel_6_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblNewLabel_6_1_1.setBounds(6, 313, 116, 28);
		internalFrame.getContentPane().add(lblNewLabel_6_1_1);

		final JTextField amount = new JTextField();
		amount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		amount.setBounds(6, 340, 69, 25);
		internalFrame.getContentPane().add(amount);
		amount.setColumns(10);
		final JComboBox comboBox_3_2 = new JComboBox(amountStrings);
		comboBox_3_2.setBounds(75, 341, 116, 27);
		internalFrame.getContentPane().add(comboBox_3_2);

		final JLabel lblNewLabel_1 = new JLabel("<html><div style='text-align: center;'> You are " + currentCalories + " calories away from achieving your daily goal!</div></html>");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(25, 98, 361, 79);
		panel2.add(lblNewLabel_1);
		
		
		btnSubmit.setOpaque(true);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		btnSubmit.setBackground(new Color(89, 83, 112));
		btnSubmit.setBounds(50, 393, 256, 82);
		internalFrame.getContentPane().add(btnSubmit);
		
		final JProgressBar progressBar2 = new JProgressBar();
		progressBar2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		progressBar2.setStringPainted(true);
		progressBar2.setMinimum(0);
		progressBar.setMaximum(2000);
		progressBar2.setValue(0);
		progressBar2.setBounds(62, 64, 287, 24);
		panel2.add(progressBar2);


		btnSubmit.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				foodListModel.addElement(list_1.getSelectedValue()); 


				JRadioButton temp = rdbtnNewRadioButton_2;
				for (JRadioButton s : inputTimeArray) {
					if (s.isSelected()) {
						temp = s; 
					}

				}
				timeListModel.addElement(comboBox_3.getSelectedItem().toString() +  ":" +
						comboBox_3_1.getSelectedItem().toString() + temp.getText());

				caloriesListModel.addElement((foodMap.get(list_1.getSelectedValue())).toString());

				currentCalories += foodMap.get(list_1.getSelectedValue());
				lblNewLabel_1.setText("<html><div style='text-align: center;'> You are " + currentCalories + " calories away from achieving your daily goal!</div></html>");
				
				progressBar2.setValue((Math.round((float)(currentCalories*100)/2000)));
				
				internalFrame.setVisible(false);
				internalFrame.setEnabled(false);
				btnSubmit.setEnabled(false);
				btnNewButton2.setVisible(true);
				btnNewButton2.setEnabled(true);

				foodLogs.setVisible(true);
				txtrFood.setVisible(true);
				txtrCalories.setVisible(true);



				// foodLogs

			}


		});



		//"Banana", "Apple", "Orange", "Egg", "Pancake", "Kiwi", "Bacon"};

		//		txtSearch.addActionListener(new java.awt.event.ActionListener() {
		//            @Override
		//            public void actionPerformed(java.awt.event.TextEvent evt) {
		//                System.out.println("whaikfjks");
		//                
		//                
		//            }
		//        });

		//internalFrame.getContentPane().add(table);





		internalFrame.setVisible(false);

		JButton btnNewButton2_1_1 = new JButton("Reset");
		btnNewButton2_1_1.setOpaque(true);
		btnNewButton2_1_1.setForeground(Color.WHITE);
		btnNewButton2_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		btnNewButton2_1_1.setBackground(new Color(89, 83, 112));
		btnNewButton2_1_1.setBounds(50, 487, 256, 82);
		internalFrame.getContentPane().add(btnNewButton2_1_1);
		
		JButton btnNewButton_4 = new JButton("Undo");
		btnNewButton_4.setBounds(77, 544, 256, 49);
		panel2.add(btnNewButton_4);
		btnNewButton_4.setBackground(Color.decode("#595370"));
		internalFrame.setBackground(Color.decode("#f7f7e1"));
		
		btnNewButton_4.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int index = foodLogs.getModel().getSize()-1;
				String calories = caloriesListModel.get(index);
				
				
				foodListModel.remove(index);
				caloriesListModel.remove(index);
				timeListModel.remove(index);
				
				currentCalories -= Integer.parseInt(calories);
				progressBar2.setValue((Math.round((float)(currentCalories*100)/2000)));
				lblNewLabel_1.setText("<html><div style='text-align: center;'> You are " + currentCalories + " calories away from achieving your daily goal!</div></html>");

			}


		});
		
		
		
		
		

		btnNewButton2_1_1.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				JRadioButton temp = rdbtnNewRadioButton_2;
				for (JRadioButton s : inputTimeArray) {
					s.setSelected(false);

				}

				list_1.clearSelection();
				amount.setText("");
				//btnNewButton2.setVisible(true);
				comboBox_3.setSelectedIndex(0);
				comboBox_3_1.setSelectedIndex(0);
				comboBox_3_2.setSelectedIndex(0);



			}


		});


		JButton btnNewButton = new JButton("Log Workout");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIconTextGap(20);
		btnNewButton.setForeground(Color.decode("#ffffff"));
		btnNewButton.setBackground(Color.decode("#595370"));
		btnNewButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
		btnNewButton.setIcon(new ImageIcon(HelloWorld.class.getResource("/Images/runicon.png")));
		btnNewButton.setBounds(55, 403, 290, 92);
		panel3.add(btnNewButton);

		JButton btnLogMeal = new JButton("Log Meal\r\n");
		btnLogMeal.setIconTextGap(45);
		btnLogMeal.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogMeal.setIcon(new ImageIcon(HelloWorld.class.getResource("/Images/forkicon.png")));
		btnLogMeal.setForeground(Color.WHITE);
		btnLogMeal.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
		btnLogMeal.setBackground(new Color(89, 83, 112));
		btnLogMeal.setBounds(55, 527, 290, 92);
		panel3.add(btnLogMeal);

		pane.addTab("" , panel4);
		panel4.setLayout(null);
		pane.setBackgroundAt(3, Color.WHITE);
		pane.setIconAt(3, new ImageIcon(HelloWorld.class.getResource("/Images/records_icon.PNG")));

		pane.addTab("" , panel5);
		panel5.setLayout(null);
		pane.setBackgroundAt(4, Color.WHITE);
		pane.setIconAt(4, new ImageIcon(HelloWorld.class.getResource("/Images/opt_icon.PNG")));

		




		



		




		btnNewButton2.setBackground(new Color(89, 83, 112));
		btnNewButton2.setOpaque(true);
		btnNewButton2.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		btnNewButton2.setBounds(77, 416, 256, 107);
		btnNewButton2.setForeground(Color.WHITE);
		panel2.add(btnNewButton2);
		
		internalFrame.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                panel1.setBackground(Color.decode("#f7f7e1"));
                btnNewButton2.setVisible(true);
                foodLogs.setVisible(true);
                txtrCalories.setVisible(true);
                txtrFood.setVisible(true);
            }
        });




		f.getContentPane().add(pane);
		pane.setSelectedIndex(2);
		f.setVisible(true);
	}
}