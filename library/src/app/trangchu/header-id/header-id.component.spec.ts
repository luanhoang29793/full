import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderIDComponent } from './header-id.component';

describe('HeaderIDComponent', () => {
  let component: HeaderIDComponent;
  let fixture: ComponentFixture<HeaderIDComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeaderIDComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderIDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
