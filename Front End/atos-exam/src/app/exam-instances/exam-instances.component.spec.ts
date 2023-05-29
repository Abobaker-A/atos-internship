import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamInstancesComponent } from './exam-instances.component';

describe('ExamInstancesComponent', () => {
  let component: ExamInstancesComponent;
  let fixture: ComponentFixture<ExamInstancesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExamInstancesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExamInstancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
