-- Leetify a given string
import Data.List
import System.IO

foo = "The quick fox jumped over the lazy brown dog."

reverseWords = unwords . map reverse . words

main = do
  putStrLn "Leetifying Engines Warming..."
  putStrLn "Please enter input string"
  putStrLn $ reverseWords foo