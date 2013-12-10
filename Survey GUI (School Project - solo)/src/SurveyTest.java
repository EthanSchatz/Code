/*
 * It handles events such as buttons "Add" "Modify" "Remove" "Remove All" "Open" and "Save"
 * It displays the different CSample object in the scroll pane by using the CSample method of "getInfo()"
 * if you run the program with an argument (a file you've already created and saved with the program,
 * it will open it from the start
 * if no argument, it opens a blank program and you can open or create a new and save.
 * It reads and saves files as objects, not as text by serializing it.
 * CS350
 * Project #3
 * Ethan Schatzline
 */


import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

//String.format(String format, Object.... args);
//String.format("Name=%s,Age=%d",name,age);
//integer:   %d (exact int), %10d (number before d = width of number. " 000000001"), %-10d(behind number), %010d(leading 0 infront instead of leading space)
//string: %s, %20s, %-20s

public class SurveyTest extends JFrame implements ActionListener {
		
		public FileInputStream fin;
		public FileOutputStream fout;
		public ObjectInputStream ois;
		public ObjectOutputStream oos;
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
		
		JLabel ParticipantNo;
		JLabel ZipCode;
		JLabel Employment;
		JLabel Impact;
		JLabel Cause;
		private int i = 1;
		private ArrayList<CSample> participants = new ArrayList<CSample>();
		ArrayList<String> nameArray;
	    private DefaultListModel names;
		JList   nameList;
		
		JScrollPane scrollPane;
		JButton bnAdd;
	    JButton bnDel;
	    JButton bnDelAll;
	    JButton bnEdit;
	    
	    
	    JButton Save;
	    JButton Open;
	    
	    

	    
	    public SurveyTest() {
			super("Survey");
			
			

		    Container c = getContentPane();
		    c.setLayout(null);		

		    ParticipantNo = new JLabel("Participant No.");
		    ParticipantNo.setSize( 200, 50 );
		    ParticipantNo.setLocation( 10, 0 );
		    ParticipantNo.setForeground(Color.BLUE);
			c.add(ParticipantNo);
			
			ZipCode = new JLabel("Zip Code");
		    ZipCode.setSize( 200, 50 );
		    ZipCode.setLocation( 130, 0 );
		    ZipCode.setForeground(Color.BLUE);
			c.add(ZipCode);
			
			Employment = new JLabel("Employment");
		    Employment.setSize( 200, 50 );
		    Employment.setLocation( 230, 0 );
		    Employment.setForeground(Color.BLUE);
		    c.add(Employment);
			
			Impact = new JLabel("Impact");
		    Impact.setSize( 200, 50 );
		    Impact.setLocation( 350, 0 );
		    Impact.setForeground(Color.BLUE);
		    c.add(Impact);
			
			Cause = new JLabel("Cause");
		    Cause.setSize( 200, 50 );
		    Cause.setLocation( 450, 0 );
		    Cause.setForeground(Color.BLUE);
		    c.add(Cause);

		    nameArray = new ArrayList<String>(); 
			names = new DefaultListModel();
			nameList = new JList(names);
		    // nameList.setVisibleRowCount( 5 );
		    nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		    nameList.setFont(new Font("Monospaced", Font.PLAIN, 12));
		    scrollPane = new JScrollPane(nameList);
		    scrollPane.setSize(540, 210);
		    scrollPane.setLocation(10, 40);
		    c.add( scrollPane );
		      
			bnAdd = new JButton("Add");
			bnAdd.setSize( 100, 50 );
			bnAdd.setLocation( 10, 260 );
			bnAdd.addActionListener(this);
			c.add(bnAdd);

			bnEdit = new JButton("Modify");
			bnEdit.setSize( 100, 50 );
			bnEdit.setLocation( 160, 260 );
			bnEdit.addActionListener(this);
			c.add(bnEdit);
			
			bnDel = new JButton("Remove");
			bnDel.setSize( 100, 50 );
			bnDel.setLocation( 310, 260 );
			bnDel.addActionListener(this);
			c.add(bnDel);
			
			bnDelAll = new JButton("Remove All");
			bnDelAll.setSize( 100, 50 );
			bnDelAll.setLocation( 450, 260 );
			bnDelAll.addActionListener(this);
			c.add(bnDelAll);

			Save = new JButton("Save");
			Save.setSize( 100, 50 );
			Save.setLocation( 590, 198 );
			Save.addActionListener(this);
			c.add(Save);

			Open = new JButton("Open");
			Open.setSize( 100, 50 );
			Open.setLocation( 590, 100 );
			Open.addActionListener(this);
			c.add(Open);

			
		    setSize( 730, 360 );
		    setLocation( 100, 100 );
		    setVisible(true);
	   }
		
	    	
	    
	    
	    public void actionPerformed(ActionEvent e) {
			if(e.getSource()==bnAdd) {
				
				CSample tempperson = new CSample(i);
				participants.add(tempperson);
			    MyDialog dialogWnd = new MyDialog(this, "Add Name", "", tempperson);
			    if (!dialogWnd.isCancelled()) {
			    	//nameArray.add(dialogWnd.getAnswer());
			    	i++;
			    	names.addElement(tempperson.getInfo());
	                nameList.setSelectedIndex(names.size()-1);
	                nameList.ensureIndexIsVisible(names.size()-1);
			    }
			}
		    else if(e.getSource()==bnEdit) {
		    	int index=nameList.getSelectedIndex();
		    	if (index>=0) {
		    		
				    MyDialog dialogWnd = new MyDialog(this, "Modify", participants.get(index).getZipCode(), participants.get(index) );
				    
				    if (!dialogWnd.isCancelled()) {
				    	//nameArray.set(index, dialogWnd.getAnswer());
				    	names.set(index, participants.get(index).getInfo());
				    }
		    	}
			}
		    else if(e.getSource()==bnDel) {
		    	int index=nameList.getSelectedIndex();
		    	if (index>=0) {
				    participants.remove(index);
				    names.remove(index);
				    if (names.size()>0) {	// not empty
				    	if (index==names.size()) {	// last one deleted
				    		index--;
				    		i--;
				    	}
				    	nameList.setSelectedIndex(index);
				    	nameList.ensureIndexIsVisible(index);
				    }
		    	}
			}
		    else if(e.getSource()==bnDelAll) {
		    	int index=nameList.getSelectedIndex();
		    	if (index>=0) {
				    participants.clear();
				    names.clear();
				    index=1;
				    i=1;
				    	}
				    	nameList.setSelectedIndex(1);
				    	nameList.ensureIndexIsVisible(1);
				    }
		    else if(e.getSource()==Save) {
		    	int returnVal = chooser.showSaveDialog(getParent());
		    	if(returnVal == JFileChooser.APPROVE_OPTION){
		    		
		    	
		    	File file= chooser.getSelectedFile();
		    	try{
		    		 
		    		FileOutputStream fout = new FileOutputStream(file.getAbsolutePath());
		    		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		    		oos.writeObject(participants);
		    		oos.close();
		    		
		     
		    	   }catch(Exception ex){
		    		   ex.printStackTrace();
		    	   }
		    	
		    }
		    }
		    else if(e.getSource()==Open) {
		    	int returnVal = chooser.showOpenDialog(getParent());
		    	if(returnVal == JFileChooser.APPROVE_OPTION){
		    		
		    	
		    	File file= chooser.getSelectedFile();
		    	
				try {
					fin = new FileInputStream(file.getAbsolutePath());
					ois = new ObjectInputStream(fin);
					participants = (ArrayList<CSample>) ois.readObject();
					names.clear();
					
					for (int i=0; i<participants.size(); i++){
						
						
				    	names.addElement(participants.get(i).getInfo());
		                nameList.setSelectedIndex(names.size()-1);
		                nameList.ensureIndexIsVisible(names.size()-1);
		                
					}
					i=participants.size()+1;
					
					
					ois.close();
				}
					
		    	catch (IOException | ClassNotFoundException e1){
		    		e1.printStackTrace();
		    	}
		    		
		    	}
		    			
		    	
		    	

		    }   
	    
	    }
	    public void open(String path){
//	    	int returnVal = chooser.showOpenDialog(getParent());
//	    	if(returnVal == JFileChooser.APPROVE_OPTION){
	    		
	    	
	    	
	    	
	    	
			try {
				fin = new FileInputStream(path);
				ois = new ObjectInputStream(fin);
				participants = (ArrayList<CSample>) ois.readObject();
				names.clear();
				
				for (int i=0; i<participants.size(); i++){
					
					
			    	names.addElement(participants.get(i).getInfo());
	                nameList.setSelectedIndex(names.size()-1);
	                nameList.ensureIndexIsVisible(names.size()-1);
	                
				}
				i=participants.get(participants.size()-1).getParticipantNo()+1;
				
				
				ois.close();
			}
				
	    	catch (IOException | ClassNotFoundException e1){
	    		e1.printStackTrace();
	    	}
	    		
	    	}
	    
		public static void main(String[] args) {
	    	SurveyTest mainWnd = new SurveyTest();
	    	if (args.length==0){
	    		
	    	}
	    	else{
	    		mainWnd.open(args[0]);
	    	}
	    	
	    }
	}


