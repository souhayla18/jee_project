package com.ensah.core.services;


import com.ensah.core.bo.Educationalelement;

import java.util.List;

public interface IEducationalelementService {


    public void addElement(Educationalelement educationalelement);

    public void updateElement(Long elementId,Educationalelement educationalelement);

    public List<Educationalelement> getAllElement();

    public void deleteElement(Long idElement);

    public Educationalelement getElementById(Long idElement);

    public Educationalelement getElementByTitle(String title);

}
