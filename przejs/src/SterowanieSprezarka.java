import javax.swing.*;
import java.awt.event.*;


public class SterowanieSprezarka extends JFrame {
private float napiecie = 0;
private JTextArea wartosc_u1;
private JTextArea wartosc_u2;
private JTextArea wartosc_t;
private JButton przycisk;

private JLabel label_u1;
private JLabel label_u2;
private JLabel label_t;
private JLabel label_napiecie_sieci;
private JLabel label_u1_v;	//label dla [v]
private JLabel label_u2_v;	//label dla [v]
private JLabel label_t_s;	//label dla [s]

private float Min; //Dolna granica napiecia
private float Max; //Gorna granica napiecia
private int t;	   //Czas blokowania sprezarki




public SterowanieSprezarka(){ //0
super("Sprezarka"); //nazwa okna

//Aktuator przycisku

ActionListener rs = new ActionListener() 
{ //1
  @Override
  public final void  actionPerformed(ActionEvent e)
{//2
       if (e.getSource() == przycisk) 
{//3

   
try { 
       Min = Float.parseFloat(wartosc_u1.getText()); //odczytania i przetwarzania wartosci u1, u2
       Max = Float.parseFloat(wartosc_u2.getText());
       t = Integer.parseInt(wartosc_t.getText());
	
      if (Min>=Max || Max<=Min) 		     //Sprawdzenia relacji u1,u2
         {label_napiecie_sieci.setText("<html>Awaria!!! <br>Wartosc gornej granicy napiecia nie moze byc <br>rowna oraz nizej wartosci dolnej jak i odwrotnie" );
	return;}

      if (t<=0 || Min <0 ||Max<=0) //Sprawdzenia dodatnosci liczb. dolna granica moze byc zero!
	{label_napiecie_sieci.setText("<html>Awaria!!! <br>Podaj niezerowe dodatne liczby u1,u2,t" );
	System.out.println(t+" " +Min+ " " +Max); 
	return; }



	
       
       
//Petla obliczenia wartosci losowej napiecia sieci

       while(true)
    {//4

	if(Min<5)  // dla przypadku kiedy Dolna granica napiecia <5
	napiecie = Min + (float) ((Math.random()*(Max+5-Min)));

	else 	   //dla wszystkich innych dozwolonych wartosci
	napiecie = (Min-5) + (float) ((Math.random()*(Max+10-Min)));
	
	System.out.println("Skok napiecia:"+String.format("%.2f", napiecie)+" [V]");

//Warunki rozruchu zabezpieczenia
	if(napiecie>Max || napiecie<Min)
           { 
//Komunikat dla operatora
label_napiecie_sieci.setText("<html>Awaria!!! Napiecie pod czas awarii: " +String.format("%.2f", napiecie)+ "[V]<br> Sprenrzarka byla wylaczona "+t+" sekund");
System.out.print("\n\n");
//Rozruch zabezpiecenia
Awaria(t);

//Wyjscie z petli dla przedlurzenia korzystania programem
break;   
	   }//5
       
     }//4
}//try
catch (IllegalArgumentException ex) { 
	System.out.println("Error message: " +ex.getMessage());
	}
}//3

}//2
};//1


setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //przy zamknieciu okna program zamyka sie
setLayout(null);

//stworzenie interfejsu
label_u1 = new JLabel("U1");
label_u1.setBounds(10,20,20,30);
label_u1_v = new JLabel("V");
label_u1_v.setBounds(65,20,20,30);
label_u2 = new JLabel("U2");
label_u2.setBounds(10,50,20,30);
label_u2_v = new JLabel("V");
label_u2_v.setBounds(65,50,20,30);
label_t = new JLabel("t");
label_t.setBounds(15,80,20,30);
label_t_s = new JLabel("s");
label_t_s.setBounds(65,80,20,30);

label_napiecie_sieci = new JLabel("Napiecie Sieci: " + napiecie+"[V]");
label_napiecie_sieci.setBounds(120,10,300,50);



wartosc_u1 = new JTextArea();
wartosc_u1.setBounds(30,30,30,15);
this.add(wartosc_u1);
wartosc_u2 = new JTextArea();
wartosc_u2.setBounds(30,60,30,15);
this.add(wartosc_u2);
wartosc_t = new JTextArea();
wartosc_t.setBounds(30,90,30,15);
this.add(wartosc_t);
przycisk = new JButton("Zaciac symulacje");
przycisk.setBounds(150,60,150,60);
przycisk.addActionListener(rs);

add(wartosc_u1);
add(wartosc_u2);
add(wartosc_t);

add(przycisk);

add(label_u1);
add(label_u1_v);
add(label_u2);
add(label_u2_v);
add(label_t);
add(label_t_s);
add(label_napiecie_sieci);
setSize(420,250);
setVisible(true);

}

//Ubezpieczenie
public final void Awaria(int t)
{ 

try{
Thread.sleep(t*1000);

}
catch(InterruptedException g) {} //wyjatek dla Thread
}

public static void main(String args[])  {
SwingUtilities.invokeLater(new Runnable() {

@Override
public final void run() {
new SterowanieSprezarka();
}
});
}
}

