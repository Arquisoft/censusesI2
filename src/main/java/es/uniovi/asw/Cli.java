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
		
		options.addOption("p", "parse", true, "Name of the Excel file .");
		
		options.addOption("ic", "input type csv", false, "Declares the type of input file as csv (Default is xlsx) .");

		options.addOption("od", "output type doc", false, "Type of the output letters as doc (Default is txt) .");
		
		options.addOption("test", "test", true, "TextTest");

	}

	public void parse() {

		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = null;
		
		VoterFileReader vfr = new ReadXlsx();
		LetterWriter lw = new LetterWriterTxt();

		try {

			cmd = parser.parse(options, args);

			if (cmd.hasOption("h")) {
				help();
			}
			if (cmd.hasOption("ic")) {
				vfr = new ReadCsv();
			}
			if (cmd.hasOption("od")) {
				lw = new LetterWriterDoc();
			}
			if (cmd.hasOption("p"))	{
				parser(cmd.getOptionValue("p"), vfr, lw);
			}
			if (cmd.hasOption("test")){
				test(cmd.getOptionValue("test"));
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
	
	private void parser(String filename, VoterFileReader reader, LetterWriter writer){
		RCensus parser = new RCensus();
		parser.ReadFile(filename, reader, writer);
	}
	
	private void test(String text){
		System.out.println(text +": The test is correct.");
	}

}
