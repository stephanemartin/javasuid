package org.example;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.security.auth.module.UnixSystem;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		//Load uniStd
		final UniStd uniStd = Native.load(UniStd.class);
		//Call unix function
		uniStd.setuid(0);

		//fun
		if(args.length == 0) {
			final long uid = new UnixSystem().getUid();
			System.out.println("Hello uid "+uid);
			System.exit(0);
		}

		final String join = String.join(" ", args);
		ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh","-c",join);
		processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
		processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
		final Process start = processBuilder.start();
		start.waitFor();
	}
}