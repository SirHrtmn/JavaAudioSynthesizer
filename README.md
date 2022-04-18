# JavaAudioSynthesizer
This repository contains a programming project, which has to be developed during a lecture at DHBW Karlsruhe. This documentation contains the results of analyzes and reasons for decisions made. 

## Entwurfsmuster

##### Einsatz begründen
UML Vorher / nacher


## Clean Architecture

##### Schichtarchitektur



## Domain Driven Design

##### Analyse der Ubiquitous Language

##### Analyse und Begründung der verwendeten Muster


## Programming Principles 

##### SOLID

##### GRASP

##### DRY


# Refactoring 1: Voice / Circuit Class
## Ausgangssituation
Der Synthesizer von jsyn benötigt zum Synthetisieren eines Tons einen Oszillator. Es gibt verschiedene Arten von verfügbaren Oszillatoren. Für die Konfiguration der Klangfarbe werden zusätzlich Filter und ein "Envelope" Tool, was das Ein- und Ausklingen des Tons bestimmt, benötigt. 

Für das Zusammenfassen von mehreren _Units_ kann die jsyn Klasse _Circuit_ benutzt werden. Anfangs wurde eine Sohnklasse von Circuit implementiert um den Oszillator vom Typ _Sinus_ zu realisieren. Ebenfalls erbte sie vom eigenen Interface _FilterEnvelopeVoice_ mit dem sie durch das Enum _VoiceCircuit_ zugänglich ist. 

![UML Grafik des Smelly Codes](./media/Refactoring1-UML-Before.png)

In der oberen Grafik ist die Klasse "SinusVoiceCircuit" zu sehen, welche die Programmierung für den gesamten Circuit beinhaltet. Dabei ist der Oszillator vom Typ _Sinus_ fest programmiert. 
    
    ...
    private void initializeUnits()
	{
        /* Typ des Oszillators hart gecoded */ 
		oscillator = new SineOscillator();
		envelope = new EnvelopeDAHDSR();
		passThrough = new PassThrough();
		output = new PassThrough();

		bandPass = new FilterBandPass();
		lowPass = new FilterLowPass();
		highPass = new FilterHighPass();
	}
    ...

Um also weitere Arten von Oszillatoren zu realisieren, müsste eine gesamt neue Circuit Klasse programmiert werden, wobei die Programmierung für die Units der Filter und des Envelopes immer diesselbe wäre. 

Dies widerspricht dem __Open / Closed Principle__ (S**O**LID), nach dem Module offen für Erweiterungen sein sollten, jedoch auch geschlossen für Modifikationen. Einerseits erschwert die bisherige Umsetzung das Entwickeln von weiteren "Voices". Andererseits kann diese Umsetzung das Einfügen von Bugs fördern, da mehrmals Code für denselben UseCase geschrieben werden muss (für die Units der Filter und des Envelopes). 

## Lösungsansatz
Anstatt für jeden Oszillatortyp eine eigenen Circuit Klasse implementieren zu müssen, könnte auch einfach eine generische Circuit Klasse erstellt werden, welche im Konstruktor ein Oszillator Objekt übergeben bekommt. Dadurch würden auch Abhängikeiten vorgebeugt, da nun nicht mehr jede Konstante des _VoiceCircuit_ Enums von einer eigenen Klasse abhängt, sondern nur noch eine zentrale Klasse bestände, welche nur mit verschiedenen Übergabeparametern verwendet wird. Somit würde mit dieser Idee auch das __Low Coupling__ Prinzip (GRASP) unterstützt werden.

Der vorgestellte Ansatz könnte wie folgt als vereinfachtes UML Diagramm dargestellt werden.

![UML Grafik von Refactorinn Ansatz](./media/Refactoring1-UML-After.png)

