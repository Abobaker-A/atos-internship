
export interface Answer {
  id: string;
  name: string;
  isSelected: boolean; // add this line
  isTrue:boolean;
  answerTime?: Date | null;
}
