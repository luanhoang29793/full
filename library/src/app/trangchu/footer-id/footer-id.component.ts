import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-footer-id',
  templateUrl: './footer-id.component.html',
  styleUrls: ['./footer-id.component.css']
})
export class FooterIDComponent implements OnInit {
  category: Observable<Category[]>

  constructor(
    private categoryService: CategoryService
    
  ) { }

  ngOnInit() {
    this.category = this.categoryService.getCategoryList();
  }

}
