import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-create-c',
  templateUrl: './create-c.component.html',
  styleUrls: ['./create-c.component.css']
})
export class CreateCComponent implements OnInit {
  category: Category = new Category();
  submitted = false;
  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) { }

  ngOnInit() {
  }
  newCategory(): void {
    this.submitted = false;
    this.category = new Category();
  }
  save() {
    this.categoryService.createCategoryr(this.category)
      .subscribe((data) => {Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: ' Create successful',
        showConfirmButton: false,
        timer: 1500
        
      });
      this.ngOnInit(); }),
       error => Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: ' Create Failure',
        showConfirmButton: false,
        timer: 1500
      });
    this.category = new Category();
  
  }
  onSubmit() {
    this.submitted = true;
    this.save();    
  }
  listCategory() {
    console.log("ve list")
    this.router.navigate(['admin/category']);
  }
}
