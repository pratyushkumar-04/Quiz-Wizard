import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizcategoriesComponent } from './quizcategories.component';

describe('QuizcategoriesComponent', () => {
  let component: QuizcategoriesComponent;
  let fixture: ComponentFixture<QuizcategoriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuizcategoriesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuizcategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
