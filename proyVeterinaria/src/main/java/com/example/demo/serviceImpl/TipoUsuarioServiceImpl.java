package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;

import com.example.demo.entity.TipoUsuario;
import com.example.demo.repository.TipoUsuarioRepository;
import com.example.demo.service.TipoUsuarioService;

public class TipoUsuarioServiceImpl implements TipoUsuarioService  {

	@Autowired
	private TipoUsuarioRepository dao;
	
	


}
