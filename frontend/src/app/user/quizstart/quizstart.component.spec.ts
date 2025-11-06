import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizstartComponent } from './quizstart.component';

describe('QuizstartComponent', () => {
  let component: QuizstartComponent;
  let fixture: ComponentFixture<QuizstartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuizstartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuizstartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
