package prototype103;

import java.util.Random;
import java.util.Timer;

public class Person{
	private int index;
	private int x;
	private int y;
	//private int AccessMatrix[][] = new int[100][100];
	private int status;
	private int N;
	private float Mtime; //movement time
	private float Wtime; //wait time
	private float Rtime; //reaction time
	private double fear;
	private double agility;
	private double radius; // radius occupied by passenger
	public long totaltime;
	public double speed;
	public Person(int a,int b, Aeroplane A)
	{
		this.N = A.N;
		/*boolean AccessMatrix[][] = new boolean[N][N];
		for(int i=0;i<N;i++){
			//System.out.println("hii");
			for(int j=0;j<N;j++){
				System.out.print("("+i +","+j+")");		
				if(g.b[i][j].getBackground()==Color.RED)
					AccessMatrix[i][j]=false;
				else
					AccessMatrix[i][j]=true;
				System.out.print(AccessMatrix[i][j]);
			}
			System.out.println("\n");
		}*/
		this.fear = 0.4;
		this.agility = 0.4;
		this.status = 0;
		this.x = a;
		this.y = b;
		//System.out.println("hello");
		Random rand = new Random();
		/*float weight = rand.nextInt(100-30)+30;
		float age =  rand.nextInt(100-10)+10;*/
		this.Mtime = 500;
				//(float) (0.40*age + 0.60*weight);
		this.Rtime = 50;
		this.Wtime = 1;
		this.totaltime = 0;
	}
	public float getMtine(){
		return this.Mtime;
	}
	public float getWtine(){
		return this.Wtime;
	}
	public float getRtine(){
		return this.Rtime;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int a){
		this.status=a;
	}
	public void setIndex(int z){
		this.index = z;
	}
	public int getx(){
		return this.x;
	}
	public int gety(){
		return this.y;
	}
	public void setx(int a){
		this.x=a;
	}
	public void sety(int b){
		this.y=b;
	}	
}
