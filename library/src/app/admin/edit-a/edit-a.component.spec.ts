import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAComponent } from './edit-a.component';

describe('EditAComponent', () => {
  let component: EditAComponent;
  let fixture: ComponentFixture<EditAComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditAComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
