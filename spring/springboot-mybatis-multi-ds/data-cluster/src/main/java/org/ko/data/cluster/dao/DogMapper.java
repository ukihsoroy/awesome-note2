package org.ko.data.cluster.dao;


import org.ko.data.cluster.domain.Dog;

public interface DogMapper {
	
	Dog findById(Integer id);
	
}
