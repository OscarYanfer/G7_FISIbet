package com.microservicio.fisibet.infraestructure.model;

public class Animal
{
	private String name;
	private String color;
	private String animalType;

	private PruebaModel pruebaModel;

	public Animal()
	{

	}

	public Animal(String name, String color, String animalType, PruebaModel pruebaModel)
	{
		this.name = name;
		this.color = color;
		this.animalType = animalType;
		this.pruebaModel = pruebaModel;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getAnimalType()
	{
		return animalType;
	}

	public void setAnimalType(String animalType)
	{
		this.animalType = animalType;
	}

	public PruebaModel getPruebaModel() {
		return pruebaModel;
	}

	public void setPruebaModel(PruebaModel pruebaModel) {
		this.pruebaModel = pruebaModel;
	}

	@Override
	public String toString() {
		return "Animal{" +
				"name='" + name + '\'' +
				", color='" + color + '\'' +
				", animalType='" + animalType + '\'' +
				", pruebaModel=" + pruebaModel +
				'}';
	}
}
