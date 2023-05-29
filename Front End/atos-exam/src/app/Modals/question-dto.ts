export interface QuestionDto {
  questionId?: string;
  selectedAnswerId: string[];
  displayTime?: Date | undefined;
  answerTime?: Date | undefined;
}
