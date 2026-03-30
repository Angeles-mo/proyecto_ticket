import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistRegister } from './artist-register';

describe('ArtistRegister', () => {
  let component: ArtistRegister;
  let fixture: ComponentFixture<ArtistRegister>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtistRegister],
    }).compileComponents();

    fixture = TestBed.createComponent(ArtistRegister);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
