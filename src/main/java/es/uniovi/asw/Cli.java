package es.uniovi.asw;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.apache.commons.cli.DefaultParser;

import org.apache.commons.cli.CommandLine;

import org.apache.commons.cli.CommandLineParser;

import org.apache.commons.cli.HelpFormatter;

import org.apache.commons.cli.Options;

import org.apache.commons.cli.ParseException;

import es.uniovi.parser.*;

public class Cli {

	private static final Logger log = Logger.getLogger(Cli.class.getName());

	private String[] args = null;

	private Options options = new Options();

	public Cli(String[] args) {

		this.args = args;

		options.addOption("h", "help", false, "show help.");

		options.addOption("v", "var", true, "Here you can set parameter .");
		
		options.addOption("p", "parse", true, "Name of the Excel file .");

	}

	public void parse() {

		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = null;

		try {

			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))

				help();

			if (cmd.hasOption("v")) {

				log.log(Level.INFO, "Using cli argument -v=" + cmd.getOptionValue("v"));
			}
				// Whatever you want to do with the setting goes here
			if (cmd.hasOption("p"))	{
				parser(cmd.getOptionValue("p"), new ReadXlsx());
			}
			else {

				log.log(Level.SEVERE, "Missing p option");

				help();

			}

		} catch (ParseException e) {

			log.log(Level.SEVERE, "Failed to parse comand line properties", e);

			help();

		}

	}

	private void help() {

		// This prints out some help

		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("Main", options);

		System.exit(0);
	}
	
	private void parser(String filename, VoterFileReader reader){
		Parser parser = new Parser();
		parser.ReadFile(filename, reader);
	
	}

}
