package com.frontend.ejercicios.Controller;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Enterprise;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Enum.Enum_RoleName;
import com.frontend.ejercicios.Service.ServiceEmployee;
import com.frontend.ejercicios.Service.ServiceProfile;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Employee")
public class ControllerEmployee {

    @Autowired
    ServiceEmployee serviceEmployee;
    ServiceProfile serviceProfile;

    public ControllerEmployee(ServiceEmployee serviceEmployee, ServiceProfile serviceProfile){
        this.serviceEmployee= serviceEmployee;
        this.serviceProfile= serviceProfile;

    }

    //Este va ser el metodo que voy a usar para que el formulario me guarde los datos
    @PostMapping("/guardar")
    public String crearUsuario (@RequestParam ("name") String name, @RequestParam("email") String email, @RequestParam("role") String role, @RequestParam("phone")String phone){ //traigo el id porque lo usé en el servicio anterior
        serviceEmployee.guardarEmpleadoperfil(phone,name,email, role,this.serviceProfile, this.serviceEmployee);
        return "redirect:/Employee/users";
    }

    @GetMapping("/users") //1 profe JuanMa
    public String consultarEmpleados(Model model){
        List<Employee> consultarEmpleado=serviceEmployee.consultarEmpleados();
        model.addAttribute("consultarEmpleado", consultarEmpleado);
        return "/Employee/consultarEmpleados";
    }


    @GetMapping("/nuevo")
    public String boton(){
      return "/Employee/nuevo";
    }

    @PostMapping("/eliminar")
    public String eliminar(@ModelAttribute Employee employee){
        serviceEmployee.eliminarUsuario(employee.getId());
        //refrescar la lista de empleados
        return "redirect:/Employee/users";
    }


    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute @Valid Employee employee,
                             BindingResult bindingResult, Model model) {

        Optional<Employee> employee1= serviceEmployee.consultarUsuario(employee.getId());

        if(employee1.isPresent()){
            employee= employee1.get();
            model.addAttribute("employee", employee);
            model.addAttribute("profile",employee.getProfile());
            model.addAttribute("enterprise",employee.getEnterprise());
        }
        return "/Employee/editar";
    }

    @RequestMapping(value="/actualizarUsuario", method = RequestMethod.POST)
    public String actualizar(@ModelAttribute("profile") Profile profile,
                             @DateTimeFormat(pattern="yyyy-mm-dd") @ModelAttribute("employee")  Employee employee,
                             @DateTimeFormat (pattern="yyyy-mm-dd") @ModelAttribute("enterprise") Enterprise enterprise, @RequestParam("idProfile") Long idProfile , @RequestParam("idEnterprise") Long idEnterprise , @RequestParam("name") String name, @RequestParam("email")  String email,
                             @RequestParam("role")  String role, @RequestParam("phone") String phone,
                             Model model) {


        employee.setName(name);
        employee.setEmail(email);
        employee.setUpdatedAt(LocalDate.now());

        if(role.equals("Admin")){
            employee.setRole(Enum_RoleName.Admin);
        } else if (role.equals("Operario")){
            employee.setRole(Enum_RoleName.Operario);
        }

        profile.setId(idProfile);

        profile.setCreatedAt(LocalDate.now());
        profile.setUpdatedAt(LocalDate.now());
        //profile.setEmployee(employee); //relación con el employee

        //Crear la enterprise
        enterprise.setId(idEnterprise);


        //Estas son las relaciones
        employee.setEnterprise(enterprise);
        employee.setProfile(profile);
        employee.getEnterprise().setId(idEnterprise);
        this.serviceEmployee.crearUsuario(employee);
        this.serviceProfile.crearPerfil(profile);
        return "redirect:/Employee/users";
    }


    /*@GetMapping("/actualizar")
    public String actualizar(@ModelAttribute Employee employee, Model model){
        return "/Employee/editar";
    }
    /*@GetMapping("/users/{id}")
    public Optional<Employee> consultarEmpleado(@PathVariable("id") Long id){
        return serviceEmployee.consultarUsuario(id);
    }
    @PatchMapping("/users")
    public void editarUsuario(@RequestBody Employee employee){
        serviceEmployee.crearUsuario(employee);
    }


    @DeleteMapping("users/{id}")
    public void eliminarUsuario(@PathVariable ("id") Long id){
        serviceEmployee.eliminarUsuario(id);
    }*/
}
