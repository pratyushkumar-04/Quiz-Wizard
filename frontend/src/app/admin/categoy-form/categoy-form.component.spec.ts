import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoyFormComponent } from './categoy-form.component';

describe('CategoyFormComponent', () => {
  let component: CategoyFormComponent;
  let fixture: ComponentFixture<CategoyFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoyFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoyFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
