package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.Edge;
import com.example.demo.util.Graph;
import com.example.demo.util.Person;
import com.example.demo.util.Relationships;
import com.example.demo.util.Vertex;

@Controller
@RequestMapping("/assign5")
public class Familymanipulations {

	public List<Person> personList = new ArrayList<>();
	public Graph graphFamily = new Graph();
	public Vertex vertex;
	public List<Vertex> ve = new ArrayList<>();
	public Edge edge;
	public int i = 0;

	@GetMapping
	@ResponseBody
	public String newCenteradding() {

		i++;

		Person person = new Person();

		person.setName("Yash");
		person.setId(Long.valueOf(1));
		person.setGender("M");

		if (!(checkPersonDuplicates(personList, person))) {
			personList.add(person);
			Vertex vert = new Vertex(person);
			ve.add(vert);
		}

		for (Person p : personList) {
			System.out.println("--------- + ==" + p.getName() + "---" + p.getId());
		}

		return null;

	}

	@GetMapping(value = "/addToFamily")
	@ResponseBody
	public String addToFamily(Person toperson, int weight, Person fromPerson) {

		Vertex toVertex = null;
		Vertex fromvertex = getvertex(fromPerson);
		int index = 0;
		
		if(checkForSiblings(toperson,fromPerson)){
			for (Vertex ve : graphFamily.getVertices()) {
				if (ve.getLabel().getId() == toperson.getId() && ve.getLabel().getGender() == toperson.getGender()
						&& ve.getLabel().getName() == toperson.getName()) {
					toVertex = ve;
					toVertex.addEdge(new Edge(fromvertex, weight));
				}
				graphFamily.getVertices().set(index, toVertex);
				index++;
			}

			fromvertex.addEdge(new Edge(toVertex, weight));
			graphFamily.addVertex(fromvertex);
			
			return "Success";
			
		}else{
			return "";
		}

	}

	

	@GetMapping(value = "/updatePerson")
	@ResponseBody
	public String updatePerson(Person personToUpdate) {

		Vertex fromvertex = getvertex(personToUpdate);

		for (Vertex ve : graphFamily.getVertices()) {
			if (ve.getLabel().getId() == personToUpdate.getId()) {

				Person pers = new Person();
				pers.setId(personToUpdate.getId());
				pers.setGender(personToUpdate.getGender());
				pers.setName(personToUpdate.getName());
				ve.setLabel(pers);
			}
		}
		graphFamily.addVertex(fromvertex);

		return null;

	}

	public boolean checkPersonDuplicates(List<Person> personList, Person p) {

		for (Person p1 : personList) {
			if (p1.getGender() == p.getGender() && p1.getId() == p.getId() && p1.getName() == p.getName()) {
				return true;
			}
		}
		return false;

	}

	public Vertex getvertex(Person person) {

		Vertex vertex = null;

		for (Vertex v : ve) {
			if (v.getLabel().getId() == person.getId() && v.getLabel().getGender() == person.getGender()
					&& v.getLabel().getName() == person.getName()) {
				return v;
			}
		}
		return vertex;
	}

	public boolean checkForSiblings(Person from, Person to) {

		Person fromFather = null;
		Person toFather = null;

		for (Vertex ve : graphFamily.getVertices()) {
			if (ve.getLabel().getId() == from.getId() && ve.getLabel().getGender() == from.getGender()
					&& ve.getLabel().getName() == from.getName()) {
				Set<Edge> edg = ve.getEdges();
				for (Edge ed : edg) {
					if (ed.getWeight() == 3) {
						fromFather = ed.getTo().getLabel();
					}
				}
			}

			if (ve.getLabel().getId() == to.getId() && ve.getLabel().getGender() == to.getGender()
					&& ve.getLabel().getName() == to.getName()) {
				Set<Edge> edg = ve.getEdges();
				for (Edge ed : edg) {
					if (ed.getWeight() == 3) {
						toFather = ed.getTo().getLabel();
					}
				}
			}
		}

		if (fromFather.getId() == toFather.getId()) {
			return false;
		} else {
			return true;
		}

	}
	
	

}
