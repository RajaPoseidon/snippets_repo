import { FlexLayout, FlexItem } from '@salt-ds/core'
import { Timeline } from './components/Timeline'
import { MainContent } from './components/MainContent'
import './App.css'

function App() {
  return (
    <FlexLayout className="app-container">
      {/* Left Panel - 30% width */}
      <FlexItem className="left-panel">
        <Timeline />
      </FlexItem>
      
      {/* Right Panel - 70% width */}
      <FlexItem grow={1} className="right-panel">
        <MainContent />
      </FlexItem>
    </FlexLayout>
  )
}

export default App
