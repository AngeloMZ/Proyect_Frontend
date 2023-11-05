package com.example.demo.controller;

import java.util.ArrayList;

//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.bean.Empresa;
import com.example.demo.bean.Usuario;
import com.example.demo.repository.BaseDatosEmpresa;

@Controller 
@RequestMapping("")
public class Controlador {
    
    Usuario usuario;
    
    @Autowired
    private BaseDatosEmpresa bd;

    @GetMapping("/")
    public String iniciar(Model model) {
        model.addAttribute("titulo", "FORMULARIO DE ACCESO");
        return "login";
    }

    @PostMapping("/")
    public String login(Usuario usuario, Model model) {
        if (usuario.getNombre().equals("edu") && usuario.getPassword().equals("edu")) {
            ArrayList<Empresa> empresas = bd.getEmpresas();
            model.addAttribute("usuario", usuario);
            this.usuario = usuario;
            model.addAttribute("empresas", empresas); 
            return "consulta";
        } else {
            return "login";
        }
    }

    @PostMapping("/insertar")
    public String insertar(Empresa empresa, Model model) {
        bd.insertar(empresa);
        ArrayList<Empresa> empresas = bd.getEmpresas();
        model.addAttribute("usuario", this.usuario);
        model.addAttribute("empresas", empresas);
        model.addAttribute("boton", "Insertar Empresa"); 
        model.addAttribute("action", "/insertar");
        model.addAttribute("empresa", null); 
        return "consulta";
    }

    @GetMapping("/borrado/{id}")
    public String borrar(@PathVariable Long id, Model model) { 
        bd.borrar(id);
        ArrayList<Empresa> empresas = bd.getEmpresas();
        model.addAttribute("empresas", empresas);
        model.addAttribute("usuario", this.usuario);
        model.addAttribute("boton", "Insertar Empresa");
        model.addAttribute("action", "/insertar");
        return "consulta";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        Empresa empresa = bd.getEmpresa(id);
        ArrayList<Empresa> empresas = bd.getEmpresas();
        model.addAttribute("empresas", empresas); 
        model.addAttribute("empresa", empresa); 
        model.addAttribute("usuario", this.usuario);
        model.addAttribute("boton", "Actualizar Empresa"); 
        model.addAttribute("action", "/modificar");
        return "consulta";
    }

    @PostMapping("/modificar")
    public String modificar2(Empresa empresa, Model model) {
        bd.modificar(empresa);
        ArrayList<Empresa> empresas = bd.getEmpresas();
        model.addAttribute("usuario", this.usuario);
        model.addAttribute("empresas", empresas); 
        model.addAttribute("empresa", null); 
        model.addAttribute("boton", "Insertar Empresa");
        model.addAttribute("action", "/insertar");
        return "consulta";
    }
}

