package Sessions;

import server.Account;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DJ on 10/02/2017.
 */

// extends TimerTask cause can't instantiate it.
public class Shesh extends TimerTask {


    private Account account;
    private Date dateIntialised;

    private volatile boolean isAlive; //must be about volatile

    private int timerCount; // keeps track of how long it will run
    private final static int maxTimeForSession = 300;
    private Timer timer;

    public static long sessionID = 872;
    private long userSessionID;

    public Shesh(Account account){

        this.account = account;
        this.dateIntialised = new Date();
        this.isAlive = true; // starting sessions boolean
        this.timer = new Timer();
        this.timerCount = 0;
        this.sessionID++;
        this.userSessionID = sessionID;

        this.timer.scheduleAtFixedRate(this, new Date(System.currentTimeMillis()), 1000 );

    }

    @Override
    public void run() {
       doTimerThing();
    }


    public void doTimerThing(){

      if(timerCount > maxTimeForSession){
          this.isAlive = false;
          this.cancel();
      }
      timerCount++;

    }

    public static long getSessionID() {
        return sessionID;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public Account getAccount() {
        return account;
    }

    public void resetTimerCount(int timerCount) {
        this.timerCount = timerCount;
    }


    public int getTimerCount() {
        return maxTimeForSession - timerCount;
    }


}
