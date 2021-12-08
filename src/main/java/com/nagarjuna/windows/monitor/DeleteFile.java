package com.nagarjuna.windows.monitor;

import java.io.File;

public class DeleteFile {
	
	public static void main(String[] args) {
		String powershellFilePath = "E:\\Grid_Director\\EclipseWorkspace\\WindowsEventLog\\cpu_utilization.ps1";
		try{

    		File file = new File(powershellFilePath);
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
		} catch (Exception e){

    		e.printStackTrace();
    		System.out.println("Delete Exception . " + e.getMessage());
			
		}
	}

}
