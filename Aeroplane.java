package prototype103;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

public class Aeroplane {

	JFrame frame;
	public static int N;	//number of columns
	public JButton[][] b = new JButton[100][100];
	
	public boolean[][] pass = new boolean[100][100];
	private Person pers[];// = new Person[180];
	private int noofPass;


	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public Aeroplane(Inputs f) {
		int i = f.getAirplaneType();
		String value = f.getNoofPassengers();

		this.noofPass = Integer.parseInt(value);
		//int disasterScale = f.getDegreeofDisaster();
		//System.out.println(i);
		//System.out.println(noofPass);
		if(i == 0){
			N = 5;
		}
		else if(i == 1)
			N = 7;
		//System.out.println(N);

		initialize(N);
			this.pers = new Person[noofPass];
		this.seatPassengers(noofPass);
		//TimeUnit.SECONDS.sleep(2);
		//long startTime = System.currentTimeMillis();
		MoveToExit mte = new MoveToExit(this,pers); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int n) {
		frame = new JFrame("Copyright © DA-IICT");
		N=n;
		frame.setLayout(new GridLayout(33,N));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int k = 0;

		int q=0;
		String number;
		int num = 1;

		for (int i = 0; i < 33; i++){
			for (int j = 0; j < N; j++){
				this.pass[i][j] = false;
			}
		}
		for (int i = 0; i < 33; i++){
			for (int j = 0; j < N; j++)
			{
				number = Integer.toString(num);



				if(j!=N/2 && i!=0 & i!=16 && i!=32){
					this.b[i][j] = new JButton();
					++num;
					frame.add(b[i][j]);
					this.b[i][j].setBackground(Color.ORANGE);
					/*Random rand = new Random();
					float p = rand.nextInt((100 - 0) + 1) + 0;
					if(p>80 && k<3*33 && q<Math.sqrt(64)){
						this.b[i][j].setBackground(Color.red);
						++k;
						++q;
						//	System.out.println("p=" + p);
						//System.out.println(q);

					}
					if(q>=Math.sqrt(2*N)){
						q=0;
					}	*/
				}
				else if(i == 0 || i == 16 ||  i == 32){
					this.b[i][j] = new JButton();
					frame.add(b[i][j]);
					this.b[i][j].setBackground(Color.ORANGE);}
				else{
					this.b[i][j] = new JButton();
					frame.add(b[i][j]);
					this.b[i][j].setBackground(Color.ORANGE);}
				this.b[i][j].setEnabled(false);
			}
		}
		this.b[0][0].setBackground(Color.green);
		this.b[0][N-1].setBackground(Color.green);
		this.b[32][0].setBackground(Color.green);
		this.b[32][N-1].setBackground(Color.green);
		this.b[16][0].setBackground(Color.green);
		this.b[16][N-1].setBackground(Color.green);
		frame.setBounds(100, 100, 500, 750);

	}

	/*public void addPerson(person pers){
		b[pers.getx()][pers.gety()].setBackground(Color.BLUE);
	}*/

	public void sitPerson(int i,int j, Person p){
		p.setx(i);
		p.sety(j);
	}
	public void seatPassengers(int m){
		//for(int i=0; i<m;i++)
		//this.pers[i] = new Person();
		int temp = m;
		int q = 0;
		int k=1;
		String num;
		int z=0;
		if(m>0){
			do{
				for(int i = 1;i<32;++i){
					if(i == 16){
						++i;
					}
					for(int j = 0;j< this.N;++j){
						if(j==this.N/2){
							++j;
						}
						Random rand = new Random();
						float p = rand.nextInt(100-1)+1;
						if(p>95 && temp>0 && this.b[i][j].getBackground()!= Color.BLUE){
							this.b[i][j].setBackground(Color.BLUE);
							this.pers[z] = new Person(i,j,this);
							//System.out.println("z="+z);
							this.pers[z].setIndex(z+1);
							num = Integer.toString(z+1);
							this.b[i][j].setText("("+i+","+j+")");
							this.pass[i][j] = true;
							--temp;
							++q;
							++z;
						}
					}
				}				
			}while(temp>0);
		}
	}
	public int getNoofPass(){
		return this.noofPass;
	}

}
