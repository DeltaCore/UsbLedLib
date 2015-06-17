package net.ccmob.usbled.lib.programs;

import java.util.ArrayList;

public class ProgramHandler {

    private static ProgramHandler instance;

    private ArrayList<BaseProgram> programList = new ArrayList<BaseProgram>();

    public static void initilize() {
        if (instance == null) {
            instance = new ProgramHandler();
        }
    }

    /**
     * @return the programList
     */
    public ArrayList<BaseProgram> getProgramList() {
        return programList;
    }

    /**
     * @param programList
     *            the programList to set
     */
    protected void setProgramList(ArrayList<BaseProgram> programList) {
        this.programList = programList;
    }

    /**
     * @return the instance
     */
    public static ProgramHandler getInstance() {
        initilize();
        return instance;
    }

    public void addProgram(BaseProgram pgm) {
        this.getProgramList().add(pgm);
    }

    public void runProgram(String id) {
        for (BaseProgram p : this.getProgramList()) {
            if (p.getId().equals(id))
                p.onExecute();
        }
    }

    public BaseProgram getProgramById(String id) {
        for (BaseProgram p : this.getProgramList()) {
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }

    public int getRunningPrograms() {
        int i = 0;
        for (BaseProgram p : this.getProgramList()) {
            if (p.isRunning())
                i++;
        }
        return i;
    }

    public void terminateAllPrograms() {
        for (BaseProgram p : this.getProgramList()) {
            if (p.isRunning()) {
                p.stop();
                while (!p.isFinished())
                    ;
            }
        }
    }
        
}
