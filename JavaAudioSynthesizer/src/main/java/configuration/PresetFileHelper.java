package configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class PresetFileHelper
{
	private PresetFileHelper()
	{
		// hide public constructor
	}

	public static void writePreset(File file, Preset preset)
	{
		try (FileWriter fileWriter = new FileWriter(file))
		{
			fileWriter.write(marshallPreset(preset));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Optional<Preset> readFile(File file)
	{
		try (Scanner scanner = new Scanner(file))
		{
			StringBuilder stringBuilder = new StringBuilder();
			while (scanner.hasNextLine())
			{
				stringBuilder.append(scanner.nextLine());
			}
			String marshalledPreset = stringBuilder.toString();
			return unmarshallPreset(marshalledPreset);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return Optional.empty();
	}

	private static String marshallPreset(Preset preset)
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance(Preset.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();
			marshaller.marshal(preset, sw);
			return sw.toString();
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
			return "";
		}
	}

	private static Optional<Preset> unmarshallPreset(String marshalledPreset)
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance(Preset.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Object unmarshalled = unmarshaller.unmarshal(new StringReader(marshalledPreset));
			return Optional.of((Preset) unmarshalled);
		}
		catch (JAXBException e)
		{
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
