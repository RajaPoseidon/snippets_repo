import { Card, H3, Text, StackLayout, FlexLayout, FlexItem, Link } from '@salt-ds/core'
import { motion } from 'framer-motion'
import { 
  CalendarIcon, 
  MessageIcon, 
  FilterIcon, 
  ClockIcon, 
  DocumentIcon 
} from '@salt-ds/icons'
import './Timeline.css'

interface TimelineStep {
  id: number
  title: string
  subtitle: string
  icon: React.ReactNode
  status: 'active' | 'completed' | 'pending'
}

const timelineSteps: TimelineStep[] = [
  {
    id: 1,
    title: 'Schedule',
    subtitle: 'Run every hour to check for updates',
    icon: <CalendarIcon size={1} />,
    status: 'completed',
  },
  {
    id: 2,
    title: 'Slack',
    subtitle: 'Find Message',
    icon: <MessageIcon size={1} />,
    status: 'completed',
  },
  {
    id: 3,
    title: 'Filter',
    subtitle: 'Filter conditions',
    icon: <FilterIcon size={1} />,
    status: 'active',
  },
  {
    id: 4,
    title: 'Formatter',
    subtitle: 'Date / Time',
    icon: <ClockIcon size={1} />,
    status: 'pending',
  },
  {
    id: 5,
    title: 'Notion',
    subtitle: 'Create Data Source Item',
    icon: <DocumentIcon size={1} />,
    status: 'pending',
  },
]

export function Timeline() {
  return (
    <StackLayout className="timeline-container" gap={0}>
      <div className="timeline-header">
        <H3>Workflow Steps</H3>
        <Text styleAs="label" color="secondary">5 steps configured</Text>
      </div>
      
      <div className="timeline-steps">
        {timelineSteps.map((step, index) => (
          <div key={step.id} className="timeline-step-wrapper">
            {/* Connector line */}
            {index > 0 && <div className="timeline-connector" />}
            
            {/* Step card with motion */}
            <motion.div
              whileHover={{ scale: 1.02, y: -2 }}
              whileTap={{ scale: 0.98 }}
              transition={{ duration: 0.2 }}
              style={{ width: '100%', cursor: 'pointer' }}
            >
              <Card 
                className={`timeline-card timeline-card--${step.status}`}
                style={{ '--saltCard-padding': '12px' } as React.CSSProperties}
              >
                <FlexLayout gap={2} align="center">
                  <FlexItem>
                    <div className="timeline-icon">
                      {step.icon}
                    </div>
                  </FlexItem>
                  
                  <FlexItem grow={1}>
                    <StackLayout gap={0}>
                      <H3 styleAs="h4">{step.title}</H3>
                      <Text color="secondary">{step.subtitle}</Text>
                    </StackLayout>
                  </FlexItem>
                  
                  <FlexItem>
                    <Text styleAs="label" color="secondary">
                      {step.id}
                    </Text>
                  </FlexItem>
                </FlexLayout>
              </Card>
            </motion.div>
            
            {/* Arrow indicator */}
            {index < timelineSteps.length - 1 && (
              <div className="timeline-arrow">
                <svg width="8" height="8" viewBox="0 0 8 8">
                  <path 
                    d="M4 0 L8 4 L4 8 L4 6 L0 6 L0 2 L4 2 Z" 
                    fill="currentColor"
                    transform="rotate(90 4 4)"
                  />
                </svg>
              </div>
            )}
          </div>
        ))}
      </div>

      <div className="timeline-footer">
        <Link href="#">Learn more</Link>
      </div>
    </StackLayout>
  )
}
