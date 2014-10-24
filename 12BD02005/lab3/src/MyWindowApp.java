
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;
 
public class MyWindowApp extends JFrame { 
	private static JLabel id;
	private static JLabel firstName;
	private static JLabel lastName;
	private static JLabel faculty;
	private static JLabel year;
	private static JPanel mainPanel;
	private static ManageStudent ms;
	static Vector<String> txt;
	static Vector<JTextField> fields;
	static Vector<JButton> buttons;
	static Vector<JTextField> newfields;
  public MyWindowApp(){
    super("Student manage App"); 
    setBounds(100, 100, 500, 500); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    ms=new ManageStudent();
    String str=ms.listStudents();
    txt=new Vector<String>();
    StringTokenizer tk=new StringTokenizer(str,"ELA");
    GridLayout grid=new GridLayout(0,7);
    mainPanel=new JPanel();
    mainPanel.setLayout(grid);
    
    
    id=new JLabel("ID");
    firstName=new JLabel("First Name");
    lastName=new JLabel("Last Name");
    faculty=new JLabel("Faculty");
    year=new JLabel("Year");
    mainPanel.add(id);
    mainPanel.add(firstName);
    mainPanel.add(lastName);
    mainPanel.add(faculty);
    mainPanel.add(year);
    mainPanel.add(new JLabel(""));
    mainPanel.add(new JLabel(""));
    while(tk.hasMoreTokens()){
    	txt.add(tk.nextToken());
    }
    
    fields=new Vector<JTextField>();
    JButton editButton;
    JButton deleteButton;
 
    buttons=new Vector<JButton>();
    for(int i=0; i < txt.size(); i++){
    	tk=new StringTokenizer(txt.get(i),"&");
       	while(tk.hasMoreTokens()){
       		fields.add(new JTextField(tk.nextToken()));
       		fields.get(fields.size()-1).setEditable(true);
    		mainPanel.add(fields.get(fields.size()-1));
    	}  	 
       	editButton=new JButton("Update");
       	deleteButton=new JButton("Delete");
       	editButton.setActionCommand(new Integer(fields.size()).toString());
    	deleteButton.setActionCommand(new Integer(fields.size()).toString());
    	buttons.add(editButton);
    	buttons.add(deleteButton);
       	editButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton btn=(JButton)e.getSource();
				int begin=new Integer(e.getActionCommand());			
					int id=new Integer(fields.get(begin-5).getText());
					ms.updateStudent(id,fields.get(begin-4).getText(),fields.get(begin-3).getText(),fields.get(begin-2).getText(),new Integer(fields.get(begin-1).getText()));
				
			}
       		
       	});
       	deleteButton.addActionListener(new  ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JButton btn=(JButton)e.getSource();
				int begin=new Integer(e.getActionCommand());
				int id=new Integer(fields.get(begin-5).getText());
				for(int i=begin-1; i >=begin -5; i--){
					mainPanel.remove(fields.get(i));
				}
				ms.deleteStudent(id);
				int index=buttons.indexOf(btn);
				mainPanel.remove(buttons.get(index));
				mainPanel.remove(buttons.get(index-1));
				repaint();
				revalidate();
			}
       		
       	});
       	mainPanel.add(editButton);
       	mainPanel.add(deleteButton);
    }
    JPanel sub=new JPanel();
    sub.setLayout(grid);
    JLabel empLbl=new JLabel("");
   	
    newfields=new Vector<JTextField>();
    
    sub.add(empLbl);
    for(int i=0; i<4; i++){
    	newfields.add(new JTextField());
    	sub.add(newfields.get(i));
    }
    JButton addNew=new JButton("ADD");
    sub.add(addNew);
    
    
    addNew.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String firstName=newfields.get(0).getText();
			String lastName=newfields.get(1).getText();
			String faculty=newfields.get(2).getText();
			String year=newfields.get(3).getText();
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(faculty);
			System.out.println(year);
			Integer id=ms.addStudent(firstName, lastName, new Integer(year), faculty);
			System.out.println("ID is: "+id);
			JTextField id_fld=new JTextField(id.toString());
			JTextField firstName_fld=new JTextField(firstName);
			JTextField lastName_fld=new JTextField(lastName);
			JTextField faculty_fld=new JTextField(faculty);
			JTextField year_fld=new JTextField(year);
			mainPanel.add(id_fld);
			mainPanel.add(firstName_fld);
			mainPanel.add(lastName_fld);
			mainPanel.add(faculty_fld);
			mainPanel.add(year_fld);
			id_fld.setEditable(false);
			firstName_fld.setEditable(false);
			lastName_fld.setEditable(false);
			faculty_fld.setEditable(false);
			year_fld.setEditable(false);
			fields.add(id_fld);
			fields.add(firstName_fld);
			fields.add(lastName_fld);
			fields.add(faculty_fld);
			fields.add(year_fld);
			JButton editButton=new JButton("EDIT"); 
			JButton deleteButton=new JButton("DELETE"); 
			
			editButton.setActionCommand(new Integer(fields.size()).toString());
	    	deleteButton.setActionCommand(new Integer(fields.size()).toString());
	    	buttons.add(editButton);
	    	buttons.add(deleteButton);
	    	System.out.println();
	    	
	    	
	    	
	       	editButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					JButton btn=(JButton)e.getSource();
					int begin=new Integer(e.getActionCommand());				
					if(btn.getText().equals("Edit")){
						btn.setText("Update");
						for(int i=begin-1; i > begin-5; i--){
							fields.get(i).setEditable(true);
						}
						 
					}else{
						btn.setText("Edit");
						int id=new Integer(fields.get(begin-5).getText());
						for(int i=begin-1; i > begin-5; i--){
							fields.get(i).setEditable(false);
						}
						ms.updateStudent(id,fields.get(begin-4).getText(),fields.get(begin-3).getText(),fields.get(begin-2).getText(),new Integer(fields.get(begin-1).getText()));
					}
					
				}
	       		
	       	});
	       	deleteButton.addActionListener(new  ActionListener(){
				public void actionPerformed(ActionEvent e) {
					JButton btn=(JButton)e.getSource();
					int begin=new Integer(e.getActionCommand());
					int id=new Integer(fields.get(begin-5).getText());
					for(int i=begin-1; i >=begin -5; i--){
						mainPanel.remove(fields.get(i));
					}
					ms.deleteStudent(id);
					int index=buttons.indexOf(btn);
					mainPanel.remove(buttons.get(index));
					mainPanel.remove(buttons.get(index-1));
					repaint();
					revalidate();
				}
	       	});
	       	mainPanel.add(editButton);
	       	mainPanel.add(deleteButton);
			repaint();
			revalidate();
		}
    	
    });
    JPanel panel=new JPanel();
    panel.add(mainPanel);
    panel.add(sub);
    add(panel);
  }
  public static void main(String[] args) {
    MyWindowApp app = new MyWindowApp(); 
    app.setVisible(true);
  }
}