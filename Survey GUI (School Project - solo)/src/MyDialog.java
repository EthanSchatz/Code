/*
 *A dialog box that pops up when one clicks "Add" from the main window
 *it handles events and contains checkboxes, textboxes, radiobuttons, and buttons
 *it remembers the info typed in and modifies the CSample object to remember what was typed in.
 * CS350
 * Project #3
 * Ethan Schatzline
 */


import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;

import javax.swing.*; 

public class MyDialog extends JDialog implements ActionListener {
	private JLabel ParticipantNo;
	private JLabel ZipCode;
	private JLabel Employment;
	private JLabel Impact;
	private JLabel Cause;
	private JLabel partno;
	private int partnumber;
    private JTextField myTextField;    
    private JButton okButton;
    private JButton cancelButton;
    
    private ButtonGroup employmenttype;
    private JRadioButton Federal;public JRadioButton StateLocal;
    private JRadioButton Private;
    private JRadioButton SelfEmployed;
    private JRadioButton Unemployed;
    
    private ButtonGroup governmentimpact;
    private JRadioButton Yes;
    private JRadioButton No;
    private JRadioButton Unknown;
    
    private JCheckBox R;    
    private JCheckBox D;    
    private JCheckBox O;
    private JCheckBox C;
    
    private CSample Participant;
    
    private boolean cancelled;
    public boolean isCancelled() { return cancelled; }
    private String zip;
    private int answer;
    public int getAnswer() { return answer; }
    // public void setAnswer(String str) { answer=str; }

    public MyDialog(JFrame owner, String title, String initStr, CSample participant) {
		super(owner, title, true);
		Participant = participant;
		partnumber = participant.getParticipantNo();
	    Container c = getContentPane();
	    c.setLayout(null);	
	    
	    Federal = new JRadioButton("Federal");
	    Federal.setSize( 100, 25 );
		Federal.setLocation( 10, 150 );
		c.add(Federal);
	    
		StateLocal = new JRadioButton("State/Local");
		StateLocal.setSize( 100, 25 );
		StateLocal.setLocation( 120, 150 );
		c.add(StateLocal);
		
		Private = new JRadioButton("Private");
		Private.setSize( 100, 25 );
		Private.setLocation( 250, 150 );
		c.add(Private);
		
		SelfEmployed = new JRadioButton("Self-Employed");
		SelfEmployed.setSize( 150, 25 );
		SelfEmployed.setLocation( 360, 150 );
		c.add(SelfEmployed);
		
		Unemployed = new JRadioButton("Unemployed");
		Unemployed.setSize( 100, 25 );
		Unemployed.setLocation( 520, 150 );
		c.add(Unemployed);
		
		employmenttype = new ButtonGroup();
		employmenttype.add(Federal);
		employmenttype.add(StateLocal);
		employmenttype.add(Private);
		employmenttype.add(SelfEmployed);
		employmenttype.add(Unemployed);
		
		//set the button equal to the correct answer
		if("Federal".equals(Participant.getEmployment())) Federal.setSelected(true);
		if("State/Local".equals(Participant.getEmployment())) StateLocal.setSelected(true);
		if("Private".equals(Participant.getEmployment())) Private.setSelected(true);
		if("Self-Employed".equals(Participant.getEmployment())) SelfEmployed.setSelected(true);
		if("Unemployed".equals(Participant.getEmployment())) Unemployed.setSelected(true);
		
		//////////////////////////////////////////////////////////////////
		
		Yes = new JRadioButton("Yes");
		Yes.setSize( 100, 25 );
		Yes.setLocation( 10, 245 );
		c.add(Yes);
		
		No = new JRadioButton("No");
		No.setSize( 100, 25 );
		No.setLocation( 150, 245 );
		c.add(No);
		
		Unknown = new JRadioButton("Unknown");
		Unknown.setSize( 100, 25 );
		Unknown.setLocation( 300, 245 );
		c.add(Unknown);
	    
		governmentimpact = new ButtonGroup();
		governmentimpact.add(Yes);
		governmentimpact.add(No);
		governmentimpact.add(Unknown);
		
		//set the button equal to the correct answer
		if("Yes".equals(Participant.getImpact())) Yes.setSelected(true);
		if("No".equals(Participant.getImpact())) No.setSelected(true);
		if("N/A".equals(Participant.getImpact())) Unknown.setSelected(true);
		
		R = new JCheckBox("Republicans(R)");
		R.setSize( 125, 25 );
		R.setLocation( 10, 320 );
		c.add(R);
	    
		D = new JCheckBox("Democrats(D)");
		D.setSize( 125, 25 );
		D.setLocation( 150, 320 );
		c.add(D);
		
		O = new JCheckBox("ObamaCare(O)");
		O.setSize( 125, 25 );
		O.setLocation( 300, 320 );
		c.add(O);
		
		C = new JCheckBox("Debt Ceiling(C)");
		C.setSize( 125, 25 );
		C.setLocation( 450, 320 );
		c.add(C);
		
		//set the buttons equal to the correct answers
		if(Participant.getCause().contains("R")) R.setSelected(true);
		if(Participant.getCause().contains("D")) D.setSelected(true);
		if(Participant.getCause().contains("O")) O.setSelected(true);
		if(Participant.getCause().contains("C")) C.setSelected(true);

	    ParticipantNo = new JLabel("Participant No.");
	    ParticipantNo.setSize( 200, 50 );
	    ParticipantNo.setLocation( 10, 10 );
	    ParticipantNo.setForeground(Color.BLUE);
		c.add(ParticipantNo);
		
		partno = new JLabel(String.format("%08d",partnumber));
	    partno.setSize( 200, 50 );
	    partno.setLocation( 100, 10 );
	    partno.setForeground(Color.MAGENTA);
		c.add(partno);
		
		
		ZipCode = new JLabel("Zip Code");
	    ZipCode.setSize( 200, 50 );
	    ZipCode.setLocation( 10, 50 );
	    ZipCode.setForeground(Color.BLUE);
		c.add(ZipCode);
		
		Employment = new JLabel("Employment Type");
	    Employment.setSize( 200, 50 );
	    Employment.setLocation( 10, 110 );
	    Employment.setForeground(Color.BLUE);
	    c.add(Employment);
		
		Impact = new JLabel("Does government shutdown impact you?");
	    Impact.setSize( 500, 50 );
	    Impact.setLocation( 10, 190 );
	    Impact.setForeground(Color.BLUE);
	    c.add(Impact);
		
		Cause = new JLabel("Who/What to blame for government shutdown?");
	    Cause.setSize( 500, 50 );
	    Cause.setLocation( 10, 280 );
	    Cause.setForeground(Color.BLUE);
	    c.add(Cause);
		
		myTextField = new JTextField(initStr);
		myTextField.setSize( 100, 30 );
		myTextField.setLocation( 100, 65 );
		c.add(myTextField);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setSize( 200, 50 );
		cancelButton.setLocation( 400, 400 );
		c.add(cancelButton);	

		okButton = new JButton("Submit");
		okButton.addActionListener(this);
		okButton.setSize( 200, 50 );
		okButton.setLocation( 100, 400 );
		c.add(okButton);	
		
	    setSize( 700, 500 );
		setLocationRelativeTo(owner);
		setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
		if (e.getSource()==okButton) {
			if (Federal.isSelected()) Participant.setEmployment("Federal");  
		    if (StateLocal.isSelected()) Participant.setEmployment("State/Local");
		    if (Private.isSelected()) Participant.setEmployment("Private");
		    if (SelfEmployed.isSelected()) Participant.setEmployment("Self-Employed");
		    if (Unemployed.isSelected()) Participant.setEmployment("Unemployed");
		    if (Yes.isSelected()) Participant.setImpact("Yes");
		    if (No.isSelected()) Participant.setImpact("No");
		    if (Unknown.isSelected()) Participant.setImpact("N/A");
		    if (R.isSelected()) answer |= 1;
		    Participant.clearCause();
		    if (R.isSelected()) Participant.setCause("R");
		    if (!R.isSelected()) Participant.setCause("-");
		    if (D.isSelected()) answer |= 2;
		    if (D.isSelected()) Participant.setCause("D");
		    if (!D.isSelected()) Participant.setCause("-");
		    if (O.isSelected()) answer |= 3;
		    if (O.isSelected()) Participant.setCause("O");
		    if (!O.isSelected()) Participant.setCause("-");
		    if (C.isSelected()) answer |= 4;
		    if (C.isSelected()) Participant.setCause("C");
		    if (!C.isSelected()) Participant.setCause("-");
		    if(Participant.getCause().length()<3 && Participant.getImpact()!="Unknown") Participant.setCause("  ");
		    
		    zip= ""+myTextField.getText()+"";
		    Participant.setZipCode(zip);
		    
		    
		    cancelled = false;
		    setVisible(false);
		}
		else if(e.getSource()==cancelButton) {
		    cancelled = true;
		    setVisible(false);
		}
    }

	
	
		
		
	
    
}

