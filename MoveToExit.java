package prototype103;


import java.awt.Color;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class MoveToExit {
	ThreadPoolExecutor executor;
	Monitor frame_2;
	static int m =0;
	public MoveToExit(Aeroplane a, Person pers[]){
		frame_2 = new Monitor();
		frame_2.frame2.setVisible(true);
		int noofPass = a.getNoofPass();
		int  corePoolSize = noofPass;
		int  maxPoolSize = noofPass;
		long keepAliveTime = 90;

		// Create the ThreadPoolExecutor
		this.executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		this.executor.allowCoreThreadTimeOut(true);
		/*		for(int i=0;i<32;++i){
			for(int j=0;j<a.N;++j){
				System.out.print(a.b[i][j]+ "\t");
			}
			System.out.println("");
		}*/
		// Starting the monitor thread as a daemon
		Thread monitor = new Thread(new MyMonitorThread(executor));
		monitor.setDaemon(true);
		monitor.start();
		// Adding the tasks
		for(int i= 0;i<noofPass;++i){
			//System.out.println("i = "+ i);
			this.executor.execute(new MyWork(a,pers[i],this.executor));

		}

	}
	public class MyWork implements Runnable
	{
		private Aeroplane aero;
		private Person per;
		ThreadPoolExecutor executor;

		public MyWork(Aeroplane a, Person pers,ThreadPoolExecutor executor)
		{
			this.aero = a;
			this.per = pers;
			this.executor = executor;
		}

		public void move(int x,int y, Aeroplane a, Person p){
			p.setx(x);
			p.sety(y);
			a.b[x][y].setBackground(Color.BLUE);
			try {
				TimeUnit.MILLISECONDS.sleep((long)p.getMtine());
				p.totaltime += p.getMtine();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*	public boolean isMoveDefined(Aeroplane a,int i, int j){
			if(a.b[i][j].getBackground() == Color.ORANGE)
				return true;
			return false;
		}*/
		public double distance(int x1, int y1, int x2, int y2){
			return Math.sqrt(Math.pow((x1-x2),2)-Math.pow((y1-y2),2));

		}
		public int[] selectExit(Aeroplane a, int x, int y){
			int N = a.N;	
			int dist[] = new int[6];
			dist[0] = (int) distance(x,y,0,0);
			dist[1] = (int) distance(x,y,0,a.N-1);
			dist[2] = (int) distance(x,y,15,0);
			dist[3] = (int) distance(x,y,15,a.N-1);
			dist[4] = (int) distance(x,y,32,0);
			dist[5] = (int) distance(x,y,32,a.N-1);
			int min = dist[0];
			int exit = 0;
			for (int i=1; i < 5; ++i){
				if (dist[i] < min){
					exit = i;
					min = dist[i];
				}
			}
			int ar[] = new int[2];
			if(exit == 0){
				ar[0] = 0;
				ar[1] = 1;
			}
			if(exit == 1){
				ar[0] = 0;
				ar[1] = N-1;
			}
			if(exit == 2){
				ar[0] = 16;
				ar[1] = 0;
			}
			if(exit == 3){
				ar[0] = 16;
				ar[1] = N-1;
			}
			if(exit == 4){
				ar[0] = 32;
				ar[1] = 0;
			}
			if(exit == 5){
				ar[0] = 32;
				ar[1] = N-1;
			}
			//System.out.println("exit"+exit+1); //TODO
			return ar;
		}

		public long[] Run(Aeroplane a, Person pers)
		{
			int exitnum[] = new int[2];
			int o = pers.getx();
			int f = pers.gety();
/*			System.out.println("pers.getWtine() = "+pers.getWtine());
			System.out.println("pers.getMtine() = "+pers.getMtine());
			System.out.println("pers.getRtine() = "+pers.getRtine());*/
			do{
				if(pers.gety()!=a.N/2 && pers.getx()!=0 && pers.getx()!=16 && pers.getx()!=32){
					//System.out.println("first if condition");
					if(pers.gety()<a.N/2){
						//System.out.println("pers.getx()<a.N/2");
						while(a.b[pers.getx()][pers.gety()+1].getBackground() == Color.BLUE){
							try {
								TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
								pers.totaltime += pers.getWtine();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(a.b[pers.getx()][pers.gety()+1].getBackground()!= Color.BLUE){
							//System.out.println("enter 1");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");

							this.move(pers.getx(), pers.gety()+1, a,pers);
						}
					}
					if(pers.gety()>a.N/2){
						//System.out.println("pers.getx()>a.N/2");
						while(a.b[pers.getx()][pers.gety()-1].getBackground() == Color.BLUE){
							try {
								TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
								pers.totaltime += pers.getWtine();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(a.b[pers.getx()][pers.gety()-1].getBackground()!= Color.BLUE){
							//System.out.println("enter 2");

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a,pers);
						}
					}					
				}
				if(pers.gety()==a.N/2 && pers.getx()!=0 && pers.getx()!=16 && pers.getx()!=32){
					if(pers.getx()<16){
						if(pers.getx()>=(16-pers.getx())){
							while(a.b[pers.getx()+1][pers.gety()].getBackground() == Color.BLUE){
								try {
									TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
									pers.totaltime += pers.getWtine();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//move down
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()+1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx()+1, pers.gety(), a, pers);
						}
						else if(pers.getx()<(16-pers.getx())){
							while(a.b[pers.getx()-1][pers.gety()].getBackground() == Color.BLUE){
								try {
									TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
									pers.totaltime += pers.getWtine();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//move up
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()-1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx()-1, pers.gety(), a, pers);
						}
					}
					else if(pers.getx()>16){
						if((pers.getx()-16)>=(32-pers.getx())){
							while(a.b[pers.getx()+1][pers.gety()].getBackground() == Color.BLUE){
								try {
									TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
									pers.totaltime += pers.getWtine();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//move down
							//System.out.println("move down");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()+1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx()+1, pers.gety(), a, pers);
						}
						else if((pers.getx()-16)<(32-pers.getx())){
							while(a.b[pers.getx()-1][pers.gety()].getBackground() == Color.BLUE){
								try {
									TimeUnit.MILLISECONDS.sleep((long)pers.getWtine());
									pers.totaltime += pers.getWtine();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//move up
							//System.out.println("move up");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()-1][pers.gety()].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx()-1, pers.gety(), a, pers);

						}
					}
				}
				if(pers.gety()==a.N/2 && (pers.getx()==0 || pers.getx()==16 || pers.getx()==32)){
					if((a.b[pers.getx()][pers.gety()-1].getBackground()==Color.BLUE && a.b[pers.getx()][pers.gety()+1].getBackground()==Color.ORANGE)){

						//System.out.println("entered in major region 1");
						if(a.N==5){
							//System.out.println("move y+1");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()+1, a, pers);
						}
						else if(a.N==7){
							//System.out.println("move y+1");
							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()+1, a, pers);

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()+1, a, pers);

						}

					}
					else if((a.b[pers.getx()][pers.gety()+1].getBackground()==Color.BLUE && a.b[pers.getx()][pers.gety()-1].getBackground()==Color.ORANGE)){

						//System.out.println("entered in major region 2");
						if(a.N==5){
							//System.out.println("move y-1");

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a, pers);
						}
						else if(a.N==7){
							//System.out.println("move y-1");

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a, pers);

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a, pers);

						}

					}
					else if((a.b[pers.getx()][pers.gety()-1].getBackground()==Color.ORANGE && a.b[pers.getx()][pers.gety()+1].getBackground()==Color.ORANGE)){
						//System.out.println("entered in major region 3");
						Random rand = new Random();
						int r = rand.nextInt(100-1)+1;
						if(r>50){
						if(a.N==5){
							//System.out.println("move y-1");

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a, pers);
						}
						else if(a.N==7){
							//System.out.println("move y-1");

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a, pers);

							a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
							a.b[pers.getx()][pers.gety()-1].setText(a.b[pers.getx()][pers.gety()].getText());
							a.b[pers.getx()][pers.gety()].setText("");
							this.move(pers.getx(), pers.gety()-1, a, pers);

						}
						}
						else {
							if(a.N==5){
								//System.out.println("move y+1");
								a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
								a.b[pers.getx()][pers.gety()].setText("");
								this.move(pers.getx(), pers.gety()+1, a, pers);
							}
							else if(a.N==7){
								//System.out.println("move y+1");
								a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
								a.b[pers.getx()][pers.gety()].setText("");
								this.move(pers.getx(), pers.gety()+1, a, pers);

								a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
								a.b[pers.getx()][pers.gety()+1].setText(a.b[pers.getx()][pers.gety()].getText());
								a.b[pers.getx()][pers.gety()].setText("");
								this.move(pers.getx(), pers.gety()+1, a, pers);

							}

						}
					}
				}



				if((pers.getx()==0 || pers.getx()==16 || pers.getx()==32) && (a.b[pers.getx()][pers.gety()+1].getBackground() == Color.GREEN || a.b[pers.getx()][pers.gety()-1].getBackground() == Color.GREEN)){
					//System.out.println("all green");
					a.b[pers.getx()][pers.gety()].setBackground(Color.ORANGE);
					a.b[pers.getx()][pers.gety()].setText("");
					pers.setStatus(1);
				}
			}while(pers.getStatus()==0);
			long ar[] = new long[3];
			ar[0] = pers.totaltime;
			ar[1] = o;
			ar[2] =f;
			return ar;
		}

		@Override
		public void run() {
			long n[] = this.Run(this.aero,this.per);
			char t1 = Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 1);
			char t2 = Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 2);
			char t3 = Thread.currentThread().getName().charAt(Thread.currentThread().getName().length() - 3);
			int number = 0;
			if(t3=='-'){
				number = Integer.parseInt(Character.toString(t2))*10 + Integer.parseInt(Character.toString(t1));
			}
			else if(t2 == '-'){
				number = Integer.parseInt(Character.toString(t1));
			}
			else if(t2!='-' && t3!='-'){
				number = Integer.parseInt(Character.toString(t3))*100+Integer.parseInt(Character.toString(t2))*10 + Integer.parseInt(Character.toString(t1)); 
			}
			frame_2.addData(" Passenger id = "+ number +" seated on "+"("+n[1]+","+n[2]+")"+ " | exited in | " +n[0]/1000+" seconds | \n");
		}
	}

	/**
	 * My monitor thread. To monitor the status of {@link ThreadPoolExecutor}
	 * and its status.
	 */
	public class MyMonitorThread implements Runnable
	{
		ThreadPoolExecutor executor;

		public MyMonitorThread(ThreadPoolExecutor executor)
		{
			this.executor = executor;
		}

		@Override
		public void run()
		{
			/*try
			{
				do
				{
						System.out.println(
								String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s, kept alive %s",
										this.executor.getPoolSize(),
										this.executor.getCorePoolSize(),
										this.executor.getActiveCount(),
										this.executor.getCompletedTaskCount(),
										this.executor.getTaskCount(),
										this.executor.isShutdown(),
										this.executor.isTerminated(),
										this.executor.getKeepAliveTime(TimeUnit.SECONDS)));
					Thread.sleep(30);
				}
				while (true);
			}
			catch (Exception e)
			{
			}*/
		}
	}
}
