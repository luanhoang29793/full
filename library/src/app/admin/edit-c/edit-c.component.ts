import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/category';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/service/category.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-edit-c',
  templateUrl: './edit-c.component.html',
  styleUrls: ['./edit-c.component.css']
})
export class EditCComponent implements OnInit {
  id:number;
  category :Category =new Category;
  constructor( 
    private route : ActivatedRoute,
    private router : Router,
    private categoryService : CategoryService,
    ) { }

  ngOnInit()
 {
   this.id= this.route.snapshot.params['id'];
   this.categoryService.getCategory(this.id)
   .subscribe(data => {
     console.log(data)
     this.category = data;
   }, error => console.log(error));  
}
updateCategory() {
  this.categoryService.updateCategory(this.id, this.category)
    .subscribe((data) => {Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Saved successful',
      showConfirmButton: false,
      timer: 1500
    });
    this.ngOnInit();
   
  }), error => Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: ' Saved Failure',
      showConfirmButton: false,
      timer: 1500
    });
  this.category = new Category();
  }
onSubmit() {
  this.updateCategory();   
}
listCategory(){
  this.router.navigate(['admin/category']);
}

}
