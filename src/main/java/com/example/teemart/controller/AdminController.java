package com.example.teemart.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.example.teemart.entity.Tshirt;
import com.example.teemart.repository.CartRepo;
import com.example.teemart.repository.TshirtRepo;
import com.example.teemart.repository.UserRepo;

@Controller
@RequestMapping("/teemart/admin")
public class AdminController {

	@Autowired
	private TshirtRepo tshirtrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CartRepo  cartrepo;
	
	
	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		long tshirtcount = tshirtrepo.count();
		long usercount	 = userrepo.count();
		long orders = cartrepo.countDistinctUserid();
		
		Long totalCartValueWrapper = cartrepo.totalCartValue();
		
		
		System.out.println("Total Cart Value: "+totalCartValueWrapper); 
		long totalCartValue = totalCartValueWrapper == null ? 0 :totalCartValueWrapper; 
		
		
		model.addAttribute("tshirtcount", tshirtcount);
		model.addAttribute("usercount", usercount);
		model.addAttribute("orders", orders);
		model.addAttribute("cartvalue", totalCartValue);
		return "admindashboard";
	}
	
	@GetMapping("/products")
	public String products(Model model)
	{
		List<Tshirt> tshirts =   tshirtrepo.findAll();
		
		model.addAttribute("tshirts", tshirts);
		
		return "adminproducts";
	}
	
	
	@GetMapping("/addTshirts")
	public String addTshirts(Model model)
	{
        model.addAttribute("tshirt", new Tshirt());
		return "add-tshirt";
	}
	
	@GetMapping("/editTshirt-{id}")
	public String editTshirt(@PathVariable int id, Model model)
	{
		Tshirt tshirt =  tshirtrepo.findById(id).get();
		model.addAttribute("tshirt", tshirt);
		return "add-tshirt";
	}
	
	
	@PostMapping("/tshirts/add")
	public String addTshirt(@ModelAttribute Tshirt tshirt,
	                        @RequestParam("imageFile") MultipartFile file , @RequestParam("imageFile2") MultipartFile file2) throws IOException {

	    if (!file.isEmpty() || !file2.isEmpty()) {
	        // Create directory if it doesn't exist
	        String uploadDir = "fileuploads/Round_T-shirt/";
	        File directory = new File(uploadDir);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        if(!file.isEmpty())
	        {		// Generate a unique file name to avoid overwrites
		        String originalFilename = file.getOriginalFilename();
		        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
		        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
		        	// Save the file
		        Path filePath = Paths.get(uploadDir, uniqueFileName);
		        Files.write(filePath, file.getBytes());
		        	// Set the filename to the tshirt entity
		        tshirt.setImage(uniqueFileName);
	        }

	        if(!file2.isEmpty())
	        {		// Generate a unique file2 name to avoid overwrites
		        String originalFilename2 = file2.getOriginalFilename();
		        String fileExtension2 = originalFilename2.substring(originalFilename2.lastIndexOf("."));
		        String uniqueFileName2 = UUID.randomUUID().toString() + fileExtension2;
		        	// Save the file2
		        Path filePath2 = Paths.get(uploadDir, uniqueFileName2);
		        Files.write(filePath2, file2.getBytes());
		        	// Set the filename2 to the tshirt entity
		        tshirt.setImage2(uniqueFileName2);
	        }
	    }

	    tshirtrepo.save(tshirt);
	    return "redirect:/teemart/admin/products";
	}

	@PostMapping("/tshirts/edit")
	public String updateTshirt(@ModelAttribute Tshirt tshirt,
	                        @RequestParam("imageFile") MultipartFile file , @RequestParam("imageFile2") MultipartFile file2) throws IOException {

	    if (!file.isEmpty() || !file2.isEmpty()) {
	        // Create directory if it doesn't exist
	        String uploadDir = "fileuploads/Round_T-shirt/";
	        File directory = new File(uploadDir);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        if(!file.isEmpty())
	        {		// Generate a unique file name to avoid overwrites
		        String originalFilename = file.getOriginalFilename();
		        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
		        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
		        	// Save the file
		        Path filePath = Paths.get(uploadDir, uniqueFileName);
		        Files.write(filePath, file.getBytes());
		        	// Set the filename to the tshirt entity
		        tshirt.setImage(uniqueFileName);
	        }

	        if(!file2.isEmpty())
	        {		// Generate a unique file2 name to avoid overwrites
		        String originalFilename2 = file2.getOriginalFilename();
		        String fileExtension2 = originalFilename2.substring(originalFilename2.lastIndexOf("."));
		        String uniqueFileName2 = UUID.randomUUID().toString() + fileExtension2;
		        	// Save the file2
		        Path filePath2 = Paths.get(uploadDir, uniqueFileName2);
		        Files.write(filePath2, file2.getBytes());
		        	// Set the filename2 to the tshirt entity
		        tshirt.setImage2(uniqueFileName2);
	        }

	    }
	    

	    tshirtrepo.save(tshirt);
	    //return "redirect:/teemart/admin/products";
	    return "redirect:/teemart/admin/editTshirt-"+tshirt.getId();
	}
	

	
	@GetMapping("/tshirt-dlt-{id}")
	public String deleteProduct(@PathVariable int id )
	{
		Tshirt tshirt = tshirtrepo.findById(id).get();
		
		if(tshirt!=null)
		{
			tshirtrepo.deleteById(id);
			
		}
		
		return "redirect:/teemart/admin/products"; 
	}
	
	@GetMapping("/orders")
	public String orders()
	{
		return "adminorders";
	}
	
	@GetMapping("/users")
	public String users()
	{
		return "adminusers";
	}
	
	
	
	
	


	
}
